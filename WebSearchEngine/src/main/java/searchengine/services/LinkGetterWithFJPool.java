package searchengine.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import searchengine.model.Page;
import searchengine.model.SiteModel;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;

public class LinkGetterWithFJPool extends RecursiveTask<List<String>> {

    private static final Logger LOGGER = LogManager.getLogger(LinkGetterWithFJPool.class);
    private static final Marker HISTORY_PARSING = MarkerManager.getMarker("HISTORY_PARSING");
    List<String> listURLtext = new ArrayList<>();
    Set<String> listURLSelect = new ConcurrentSkipListSet<>();
    static Set<String> visitedLinks = new ConcurrentSkipListSet<>();
    List<String> finalList = new ArrayList<>();
    String url;
    private final SiteModel site;
    public static Map<String, Object> htmlStore = new HashMap<>();


    public LinkGetterWithFJPool(String url, SiteModel site) {
        this.url = url;
        this.site = site;
    }


    @Override
    protected List<String> compute() {
        visitedLinks.add(url);
        Document docNeed = null;

        Connection.Response response = null;
        int code = 0;
        try {
            response = Jsoup.connect(url)
                    .userAgent(
                            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                    .timeout(10000)
                    .execute();
            docNeed = response.parse();
            code = response.statusCode();
        } catch (IOException e) {
            System.out.println("io - " + e);
            LOGGER.error("Error when reading url: " + url + "with exception: " + e);
        }

        String content = docNeed.html();
        String lemmatized_content = "";
        Page page = new Page(url, code, content, lemmatized_content, site);
        htmlStore.put(url, page);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            LOGGER.error("Error when ==> Thread.sleep " + e);
            e.printStackTrace();
        }

        for (Element docs : docNeed  // in the page code, we search for elements that meet the requirement
                .select("a[href]"))
        {
            String element = docs
                    .attr("abs:href"); // you may not use this variable, but it is clearer with it
            if (element.contains("#")) {  // getting rid of the "#" in the links
                int indexOfNeedless = element.indexOf("#");
                element = element.substring(0, indexOfNeedless);
            }
            listURLtext.add(element);
        }

    /*
    We filter the received links, including excluding previously passed ones
     */
        for (String select : listURLtext) {
            if (select.startsWith(url) && !select.endsWith("pdf") && !select.endsWith("jpg") && !visitedLinks
                    .contains(select)) {
                listURLSelect.add(select);
                LOGGER.info(HISTORY_PARSING, " listURLSelect is {} ", listURLSelect);
            }
        }

        List<LinkGetterWithFJPool> taskList = new ArrayList<>();
        for (String url : listURLSelect) {
            LinkGetterWithFJPool task = new LinkGetterWithFJPool(url, site);
            task.fork();
            taskList.add(task);
        }

        for (LinkGetterWithFJPool task : taskList) {
            try {
                finalList.addAll(task.join());
            } catch (Exception e) {
                System.out.println(" Error into -> " + task.url);
                LOGGER.debug(HISTORY_PARSING, " Error into -> " + task.url);
            }
        }
        finalList.add(url);
        return finalList;
    }
}