package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.model.Lemma;
import searchengine.model.Page;
import searchengine.model.SiteModel;
import searchengine.repository.LemmaRepository;
import searchengine.repository.PageRepository;
import searchengine.repository.SiteModelRepository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
public class SiteParseService {

    private static final Logger LOGGER = LogManager.getLogger(SiteParseService.class);
    private static final Marker HISTORY_PARSING = MarkerManager.getMarker("HISTORY_PARSING");
    private static final Marker WORD_SEARCH_HISTORY = MarkerManager.getMarker("WORD_SEARCH_HISTORY");


    private final SitesList sites;
    private final SiteModelRepository siteModelRepository;
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;

    public List<SiteModel> saveSites_toDB(){
        List<Site> sitesList = sites.getSites();
        List<SiteModel> siteModelList = new ArrayList<>();
        for(Site site : sitesList){
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


    public Set<String> retrievePagesFromSite(SiteModel site){
        String url = site.getUrl();
        List<String> resultList = new ForkJoinPool().invoke(new LinkGetterWithFJPool(url, site));
        Set<String> allPagesSite = cleanDuplicates(resultList);
        return allPagesSite;
    }


    public static Set<String> cleanDuplicates(Collection<String> collection) {
        Set<String> elements = new TreeSet<>();
        for (String element : collection) {
            elements.add(element);
        }
        return elements;
    }


    public List<Page> saveAllPagesSite_toDB(SiteModel siteModel) {
        List<Page> pageList = new ArrayList<>();
        Set<String> allPagesSite = retrievePagesFromSite(siteModel);
        for(String url : allPagesSite){
            Page currentPage = (Page) LinkGetterWithFJPool.htmlStore.getOrDefault(url, "No data available");
            pageList.add(currentPage);
            pageRepository.save(currentPage);
        }
        return pageList;
    }


    public void savelemmatizedContent_toPageTable(List<Page> pageList) {
        for(Page page : pageList){
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
                    lemmsInTitle =  Morphology.getSetLemmas(strInTitle).getValue0();
                    System.out.println("lemmsInTitle = " + lemmsInTitle);
                    LOGGER.info(HISTORY_PARSING, "Parsing of the tags <title> the page:  " + page.getPath() + " was performed successfully");
                } catch (IOException e) {
                    LOGGER.error("Error when parsing of the tags <title> the page: " + page.getPath() + "for getting lemmas");
                    e.printStackTrace();
                }

                try {
                    lemmsInBody =  Morphology.getSetLemmas(strInBody).getValue0();
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

                    if(rankOflemms.containsKey(lemmaInBody)) {
                        rankOflemms.put(lemmaInBody, rankOflemms.get(lemmaInBody) + (rankInBody * rankOfLemmaInBody));
                    }
                    else {
                        rankOflemms.put(lemmaInBody, rankInBody * rankOfLemmaInBody);
                    }
                });

                // TODO

                rankOflemms.forEach((key, value) -> {
                    String current_string_lemma = key;
                    Double rankOfLemma = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();

                    try {
                        Query query = session.createQuery("select l from Lemma l where l.lemma = :itemlemma").setParameter("itemlemma", current_string_lemma);
                        Lemma current_lemma = (Lemma) query.getSingleResult();
                        Integer iDlemma = current_lemma.getId();

                        MyIndex myIndex = new MyIndex();
                        myIndex.setPage_id(idPage);
                        myIndex.setLemma_id(iDlemma);
                        myIndex.setRankOflemma(rankOfLemma);
                        session.save(myIndex);
                    }
                    catch (NoResultException enre) {
                        LOGGER.error("Error when writing to myIndex table occurred" + enre);
                        LOGGER.error("current_string_lemma = " + current_string_lemma + "\n" +
                                "rankOfLemma: " + value);
                        enre.printStackTrace();
                        return;
                    }
                    catch (Exception e) {
                        LOGGER.error("Error when writing to myIndex table occurred" + e);
                        LOGGER.error("current_string_lemma = " + current_string_lemma + "\n" +
                                "rankOfLemma" + value);
                        e.printStackTrace();
                        return;
                    }
                });










            }
            catch (IOException ioe){
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

            if(lemmasThatAreInDB.contains(lemmaStringFromPage)) {
                Lemma receivedLemma = lemmaRepository.findByLemma(lemmaStringFromPage);
                receivedLemma.setFrequency(receivedLemma.getFrequency() + 1);
                lemmaRepository.save(receivedLemma);
            }
            else{
                Lemma createNewlemma = new Lemma(lemmaStringFromPage, 1);
                lemmaRepository.save(createNewlemma);
            }
        });
    }


}
