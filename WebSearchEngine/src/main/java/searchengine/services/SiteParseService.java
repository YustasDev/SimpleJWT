package searchengine.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.hibernate.Transaction;
import org.javatuples.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.dto.indexing.IndexingResult;
import searchengine.dto.indexing.SearchData;
import searchengine.model.*;
import searchengine.repository.*;

import javax.persistence.Query;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Log4j2
public class SiteParseService {

    private static final Logger LOGGER = LogManager.getLogger(SiteParseService.class);
    private static final Marker HISTORY_PARSING = MarkerManager.getMarker("HISTORY_PARSING");
    private static final Marker WORD_SEARCH_HISTORY = MarkerManager.getMarker("WORD_SEARCH_HISTORY");

    private final SitesList sites;
    private final SiteModelRepository siteModelRepository;
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;
    private final MyIndexRepository myIndexRepository;
    private final RelevanceRepository relevanceRepository;
    private final CustomoutputRepository customoutputRepository;

    public List<SiteModel> saveSites_toDB() {
        List<Site> sitesList = sites.getSites();
        List<SiteModel> siteModelList = new ArrayList<>();
        for (Site site : sitesList) {
            String urlSite = site.getUrl();
            SiteModel.Status status = SiteModel.Status.INDEXING;
            String last_error = "";
            String site_name = site.getName();
            SiteModel siteModel = new SiteModel();
            siteModel.setStatus(status);
            siteModel.setUrl(urlSite);
            siteModel.setSite_name(site_name);
            siteModel.setStatus_time(LocalDateTime.now());
            siteModel.setLast_error(last_error);
            siteModelRepository.save(siteModel);
            siteModelList.add(siteModel);
        }
        return siteModelList;
    }


    public Set<String> retrievePagesFromSite(SiteModel site) {
        String url = site.getUrl();
        List<String> resultList = new ForkJoinPool().invoke(new LinkGetterWithFJPool(url, site));
        Set<String> allPagesSite = cleanDuplicates(resultList);
        return allPagesSite;
    }


    public IndexingResult indexingOnePage(String url) {
        boolean containsUrl = false;
        List<Page> pageList = new ArrayList<>();
        List<Site> sitesList = sites.getSites();
        for (Site site : sitesList) {
            String urlSite = site.getUrl();
            if (url.startsWith(urlSite)) {
                containsUrl = true;
            }
        }
        if (containsUrl) {
            List<String> resultList = new ForkJoinPool().invoke(new LinkGetterWithFJPool(url, null));
            Page currentPage = (Page) LinkGetterWithFJPool.htmlStore.getOrDefault(url, "No data available");
            pageRepository.save(currentPage);
            pageList.add(currentPage);
            saveIndexingData_toDB(pageList);
            return new IndexingResult(true, null);
        } else {
            return new IndexingResult(false, "Данная страница находится за пределами сайтов, " +
                    "указанных в конфигурационном файле");
        }
    }


    public static Set<String> cleanDuplicates(Collection<String> collection) {
        Set<String> elements = new TreeSet<>();
        for (String element : collection) {
            elements.add(element);
        }
        return elements;
    }


    public List<Page> saveAllPagesSite_toDB(SiteModel siteModel) throws InterruptedException {
        List<Page> pageList = new ArrayList<>();
        Set<String> allPagesSite = retrievePagesFromSite(siteModel);
        for (String url : allPagesSite) {
            if (!Thread.currentThread().isInterrupted()) {
                Page currentPage = (Page) LinkGetterWithFJPool.htmlStore.getOrDefault(url, "No data available");
                pageList.add(currentPage);
                pageRepository.save(currentPage);
            } else {
                throw new InterruptedException("Выполнение индексирования прервано!");
            }
        }
        return pageList;
    }


    public void saveIndexingData_toDB(List<Page> pageList) {
        for (Page page : pageList) {
            String originalContent = page.getContent();
            String cleanContent = Jsoup.clean(originalContent, Whitelist.none());

            cleanContent = cleanContent.replaceAll("[^А-Яа-я \\pP-]", "").replaceAll("\\sр\\s", "")
                    .replaceAll("\\sГБ\\s", "").replaceAll("[\\p{P}&&[^\\-]]", " ");

            //===================================== insert 'lemmatized_content' field in page table ============>
            try {
                String lemmatized_content = Morphology.getSetLemmas(cleanContent).getValue1();
                page.setLemmatized_content(lemmatized_content);
                pageRepository.save(page);
                LOGGER.info(HISTORY_PARSING, "Parsing of the page:  " + page.getPath() + " was performed successfully");
                //====================================== END insert 'lemmatized_content' field in page table ========<
                // we add up separately, the lemmas that need to be updated and which need to be written down
                writeLemmas_toDB(cleanContent);
                writeMyIndex_toDB(page, originalContent);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                LOGGER.error("Error when parsing ==> " + page.getPath() + " with Exception: " + ioe);
            }
        }
    }


    private void writeLemmas_toDB(String cleanContent) throws IOException {
        Map<String, Integer> lemmsMapFromPage = null;
        lemmsMapFromPage = Morphology.getSetLemmas(cleanContent).getValue0();
        List<String> lemmasThatAreInDB = new ArrayList<>();
        List<Lemma> lemmaList = lemmaRepository.findAll();  // we get lemmas from DB
        for (Lemma lemmaObj : lemmaList) {
            String lemmaStringFromDB = lemmaObj.getLemma();
            lemmasThatAreInDB.add(lemmaStringFromDB);
        }
        lemmsMapFromPage.forEach((key, value) -> {          // we get lemmas from current Page
            String lemmaStringFromPage = key;

            if (lemmasThatAreInDB.contains(lemmaStringFromPage)) {
                Lemma receivedLemma = lemmaRepository.findByLemma(lemmaStringFromPage);
                receivedLemma.setFrequency(receivedLemma.getFrequency() + 1);
                lemmaRepository.save(receivedLemma);
            } else {
                Lemma createNewlemma = new Lemma(lemmaStringFromPage, 1);
                lemmaRepository.save(createNewlemma);
            }
        });
    }

    private void writeMyIndex_toDB(Page page, String originalContent) {

        Document docTitle = Jsoup.parse(originalContent);
        String textInTitle = docTitle.select("title").text();

        StringBuffer textBody = new StringBuffer();
        for (Element result : docTitle.select("body")) {
            String text = result.text();
            textBody.append(text);
        }
        String textInBody = String.valueOf(textBody);

        String strInTitle = Jsoup.clean(textInTitle, Whitelist.none())
                .replaceAll("[^А-Яа-я \\pP-]", "").replaceAll("\\sр\\s", "")
                .replaceAll("\\sГБ\\s", "").replaceAll("[\\p{P}&&[^\\-]]", " ");

        String strInBody = Jsoup.clean(textInBody, Whitelist.none())
                .replaceAll("[^А-Яа-я \\pP-]", "").replaceAll("\\sр\\s", "")
                .replaceAll("\\sГБ\\s", "").replaceAll("[\\p{P}&&[^\\-]]", " ");

        Map<String, Integer> lemmsInTitle = new HashMap<>();
        Map<String, Integer> lemmsInBody = new HashMap<>();
        Map<String, Double> rankOflemms = new HashMap<>();
        try {
            lemmsInTitle = Morphology.getSetLemmas(strInTitle).getValue0();
            System.out.println("lemmsInTitle = " + lemmsInTitle);
            LOGGER.info(HISTORY_PARSING, "Parsing of the tags <title> the page:  " + page.getPath() + " was performed successfully");
        } catch (IOException e) {
            LOGGER.error("Error when parsing of the tags <title> the page: " + page.getPath() + "for getting lemmas");
            e.printStackTrace();
        }

        try {
            lemmsInBody = Morphology.getSetLemmas(strInBody).getValue0();
            System.out.println("lemmsInBody = " + lemmsInBody);
            LOGGER.info(HISTORY_PARSING, "Parsing of the tags <body> the page:  " + page.getPath() + " was performed successfully");
        } catch (IOException e) {
            LOGGER.error("Error when parsing of the tags <body> the page: " + page.getPath() + "for getting lemmas");
            e.printStackTrace();
        }
        //================== fill in table myIndex
        final Double rankOfLemmaInTitle = 1.0;  // coefficient for the field "title"
        final Double rankOfLemmaInBody = 0.8;   // coefficient for the field "body"
        lemmsInTitle.forEach((key, value) -> {
            String lemmaInTitle = key;
            Double rankInTitle = Double.valueOf(value);
            rankOflemms.put(lemmaInTitle, rankInTitle);
        });

        lemmsInBody.forEach((key, value) -> {
            String lemmaInBody = key;
            Double rankInBody = Double.valueOf(value);

            if (rankOflemms.containsKey(lemmaInBody)) {
                rankOflemms.put(lemmaInBody, rankOflemms.get(lemmaInBody) + (rankInBody * rankOfLemmaInBody));
            } else {
                rankOflemms.put(lemmaInBody, rankInBody * rankOfLemmaInBody);
            }
        });

        rankOflemms.forEach((key, value) -> {
            String current_string_lemma = key;
            Double rankOfLemma = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
            Lemma current_lemma = lemmaRepository.findByLemma(current_string_lemma);
            Integer iDlemma = current_lemma.getId();

            MyIndex myIndex = new MyIndex();
            myIndex.setPage_id(page.getId());
            myIndex.setLemma_id(iDlemma);
            myIndex.setRankOflemma(rankOfLemma);
            myIndexRepository.save(myIndex);
        });
    }


    public List<SearchData> getSearchResult(String searchQuery) throws IOException {
        Map<String, Integer> lemmasInQuery = new HashMap<>();
        List<CustomOutput> list_CustomOutput = new ArrayList<>();

        try {
            lemmasInQuery = Morphology.getSetLemmas(searchQuery).getValue0();
        } catch (IOException e) {
            LOGGER.error(WORD_SEARCH_HISTORY, "Error: " + e + "\n when parsing of the searchQuery: " + searchQuery);
            e.printStackTrace();
            throw new IOException("Error when parsing of the searchQuery: " + searchQuery);
        }

        Map<Lemma, Integer> lemmasQueryByFrequency = new HashMap<>();
        List<Lemma> listLemmasInQuery = new ArrayList<>();
        Set<Integer> setOfPageNumbers = new HashSet<>();
        Set<Lemma> fullSetlemmasInQuery = new HashSet<>();

        lemmasInQuery.forEach((key, value) -> {
            Lemma lemmaFromQuery = lemmaRepository.findByLemma(key);
            if (lemmaFromQuery != null) {
                fullSetlemmasInQuery.add(lemmaFromQuery);
            } else {
                String newKey = StemmerPorterRU.stem(key);
                List<Lemma> lemmasFromQuerry = lemmaRepository.findByLemmaStartsWith(newKey);
                fullSetlemmasInQuery.addAll(lemmasFromQuerry);
            }
        });

        //=================== Let's eliminate the most common lemmas (item 5.2) ===================================>
        for (Lemma lemmaFromQuery : fullSetlemmasInQuery) {
            Integer numberOfPages = Streamable.of(pageRepository.findAll()).toList().size();
            int thresholdFrequency = (int) (numberOfPages * 0.8);

            if (lemmaFromQuery != null && lemmaFromQuery.getFrequency() < thresholdFrequency) {
                lemmasQueryByFrequency.put(lemmaFromQuery, lemmaFromQuery.getFrequency());
            }
        }
//=========================================================================================================<
        lemmasQueryByFrequency.entrySet().stream().sorted(Map.Entry.<Lemma, Integer>comparingByValue())
                .forEach(x -> listLemmasInQuery.add(x.getKey()));
        // ================= Item 5.6 ======================>

        for (Lemma lem : listLemmasInQuery) {
            List<MyIndex> list_myIndex = myIndexRepository.findByLemma_Id(lem.getId());
            for (MyIndex myIndex : list_myIndex) {
                setOfPageNumbers.add(myIndex.getPage_id());
            }
        }

        HashMap<Integer, Pair> mapRelevance = new HashMap<>();
        HashMap<Integer, Double> pre_MapRelevance = new HashMap<>();
        Double abs_relevance = 0.0;
        if (setOfPageNumbers.size() > 0) {
            for (Integer numberPage : setOfPageNumbers) {
                Double sumRanks_unnecessaryLength = myIndexRepository.findSumRankOfLemmaByPageId(numberPage);
                Double sumRanks = new BigDecimal(sumRanks_unnecessaryLength).setScale(2, RoundingMode.HALF_UP).doubleValue();
                pre_MapRelevance.put(numberPage, sumRanks);
                if (sumRanks > abs_relevance) {
                    abs_relevance = sumRanks;
                }
            }

            double finalAbs_relevance = abs_relevance;
            pre_MapRelevance.forEach((key, value) -> {
                Double relative_relevance = new BigDecimal(value / finalAbs_relevance).setScale(2, 1).doubleValue();
                Pair<Double, Double> tuple = new Pair<Double, Double>(value, relative_relevance);  // value --> is absolut_relevance this page
                mapRelevance.put(key, tuple);
            });

//            Query truncate = session.createNativeQuery("truncate relevance");
//            truncate.executeUpdate();
//            transaction.commit();

            relevanceRepository.truncateRelevanceTable();

            mapRelevance.forEach((key, value) -> {
                Relevance rev = new Relevance();
                rev.setPage(key);
                rev.setAbsolute_relevance((Double) value.getValue0());
                rev.setRelative_relevance((Double) value.getValue1());
                relevanceRepository.save(rev);
            });
// =================== item 5.7 ====================================>
            List<CustomOutput> customOutputList = new LinkedList<>();
            List<Relevance> relevances = relevanceRepository.findAll();
            Collections.sort(relevances);

            customoutputRepository.truncateCustomoutputTable();

            for (Relevance rev : relevances) {
                Integer pageId = rev.getPage();
                Page current_Page = pageRepository.findById(pageId);
                String uri = current_Page.getPath();
                String content = current_Page.getContent();
                Document doc = Jsoup.parse(content);
                String title = doc.select("title").text();
                Double relevanceItem = rev.getAbsolute_relevance();
                StringBuffer pre_snippet = new StringBuffer();

                for (Lemma lemma_toSearch : listLemmasInQuery) {
                    String matchingWord = lemma_toSearch.getLemma();
                    Elements elements = doc.select("*:containsOwn(" + matchingWord + ")");
                    if (elements.isEmpty()) {
                        String lemmatized_content = current_Page.getLemmatized_content();
                        Matcher matcher = Pattern.compile(".*\\s(.*\\|" + matchingWord + ")\\s.*").matcher(lemmatized_content);
                        matcher.matches();
                        try {
                            String findWord = matcher.group(1);
                            String[] words = findWord.split("\\|");
                            matchingWord = words[0];
                        } catch (IllegalStateException ise) {
                            LOGGER.warn(WORD_SEARCH_HISTORY, "The word: '" + matchingWord + "' was not found on page: " + pageId);
                        }
                        elements = doc.select("*:containsOwn(" + matchingWord + ")");
                    }
                    if (!elements.isEmpty()) {
                        for (Element element : elements) {
                            String swap = "<b>" + matchingWord + "<b>";
                            String editedExpression = element.toString().replaceAll("(?iu)" + matchingWord, swap);
                            pre_snippet.append(editedExpression + "\n");
                        }
                        String snippet = pre_snippet.toString();

                        CustomOutput customOutput = new CustomOutput();
                        customOutput.setUri(uri);
                        customOutput.setTitle(title);
                        customOutput.setSnippet(snippet);
                        customOutput.setRelevance(relevanceItem);
                        customoutputRepository.save(customOutput);
                    }
                }
            }

//            list_CustomOutput = session.createQuery("select cop from CustomOutput cop where cop.id IN" +
//                    " (select max(cop.id) as mid from CustomOutput cop group by cop.uri)").getResultList();
//
//
//
//            list_CustomOutput = customoutputRepository.select


        }
        // list_CustomOutput    todo do something further


    }
}
