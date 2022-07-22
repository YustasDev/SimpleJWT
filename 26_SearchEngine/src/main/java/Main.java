import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

import models.Lemma;
import models.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.safety.Whitelist;
import services.LinkGetterWithFJPool;
import services.Morphology;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Main {

  public static final String URL_NEED = "http://www.playback.ru/";
  private static String recordedFile = "output.txt";
  private static final Logger LOGGER = LogManager.getLogger(Main.class);
  private static final Marker HISTORY_PARSING = MarkerManager.getMarker("HISTORY_PARSING");

  public static void main(String[] args) {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();


    List<String> resultList = new ForkJoinPool()
        .invoke(new LinkGetterWithFJPool(URL_NEED));
   // System.out.println("Все найденные URL: " + resultList);
    Set<String> nonDuplicates = cleanDuplicates(resultList);
    System.out.println("URL без дубликатов:");
    nonDuplicates.forEach((e) -> {
      System.out.println(e);
    });

    for (String url : nonDuplicates) {
      Page currentPage = (Page) LinkGetterWithFJPool.htmlStore.getOrDefault(url, "No data available");
      session.save(currentPage);
    }

    transaction.commit();
   // session.close();
   // sessionFactory.close();

    //=========================>

    List<Page> pageList = session.createQuery("from Page").getResultList();
    for(Page page : pageList){
      StringBuilder sbContent = new StringBuilder();
      List<String> contentList = new ArrayList<>();
      String originalContent = page.getContent();
      String cleanContent = Jsoup.clean(originalContent, Whitelist.none());

      cleanContent = cleanContent.replaceAll("[^А-Яа-я -]", "").replaceAll("\\sр\\s", "").replaceAll("\\sГБ\\s", "");
      Map<String, Integer> lemmsMapFromPage = null;
      try {
        lemmsMapFromPage =  Morphology.getSetLemmas(cleanContent);
        System.out.println(lemmsMapFromPage);
        LOGGER.info(HISTORY_PARSING, "Parsing of the page:  " + page.getPath() + " was performed successfully");
      } catch (IOException e) {
        LOGGER.error("Error when parsing ==> " + page.getPath() + "into lemmas");
        e.printStackTrace();
      }
      // TODO we add up separately, the lemmas that need to be updated and which need to be written down
      List<String> lemmasThatAreInDB = new ArrayList<>();
      List<Lemma> lemmaList = session.createQuery("from Lemma").getResultList();  // we get lemmas from DB
      for(Lemma lemmaObj : lemmaList){
        String lemmaStringFromDB = lemmaObj.getLemma();
        lemmasThatAreInDB.add(lemmaStringFromDB);
        }

      lemmsMapFromPage.forEach((key, value) -> {                                    // we get lemmas from current Page
        String lemmaStringFromPage = key;
        if(lemmasThatAreInDB.contains(lemmaStringFromPage)) {
          // Query query = session.createNativeQuery("select * from lemma where lemma =?", Lemma.class);
          // query.setParameter(1, lemmaStringFromPage);
          Query query = session.createQuery("select l from Lemma l where l.lemma = :itemlemma").setParameter("itemlemma", lemmaStringFromPage);
          Lemma receivedLemma = (Lemma) query.getSingleResult();
          receivedLemma.setFrequency(receivedLemma.getFrequency() + 1);
          session.beginTransaction();
          session.saveOrUpdate(receivedLemma);
          if (transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
            transaction.commit();
          }
          }
        else{
            Lemma createNewlemma = new Lemma(lemmaStringFromPage, 1);
            session.save(createNewlemma);
          }
      });
      if (transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
        transaction.commit();
      }

//      String strInTitle = Jsoup.clean(originalContent, Whitelist.none().addTags("title"))
//              .replaceAll("[^А-Яа-я -]", "").replaceAll("\\sр\\s", "").replaceAll("\\sГБ\\s", "");
//      String strInBody = Jsoup.clean(originalContent, Whitelist.none().addTags("body"))
//              .replaceAll("[^А-Яа-я -]", "").replaceAll("\\sр\\s", "").replaceAll("\\sГБ\\s", "");

      Document docTitle = Jsoup.parse(originalContent);

      String textTitle = docTitle.select("title").text();

      for (Element result : docTitle.select("title")) {
        String text = ((TextNode) result.childNode(0)).getWholeText();
        System.out.println(text);
      }

      for (Element result : docTitle.select("body")) {
        String text = result.text();
        System.out.println(text);
      }






      Map<String, Integer> lemmsInTitle = null;
      Map<String, Integer> lemmsInBody = null;
//      try {
//        lemmsInTitle =  Morphology.getSetLemmas(strInTitle);
//        System.out.println(lemmsMapFromPage);
//        LOGGER.info(HISTORY_PARSING, "Parsing of the tags <title> the page:  " + page.getPath() + " was performed successfully");
//      } catch (IOException e) {
//        LOGGER.error("Error when parsing of the tags <title> the page: " + page.getPath() + "for getting lemmas");
//        e.printStackTrace();
//      }
//
//      try {
//        lemmsInBody =  Morphology.getSetLemmas(strInBody);
//        System.out.println(lemmsMapFromPage);
//        LOGGER.info(HISTORY_PARSING, "Parsing of the tags <body> the page:  " + page.getPath() + " was performed successfully");
//      } catch (IOException e) {
//        LOGGER.error("Error when parsing of the tags <body> the page: " + page.getPath() + "for getting lemmas");
//        e.printStackTrace();
//      }

      int x = 13;














      }


    }



  public static Connection getConnection() throws SQLException {
    ResourceBundle resource = ResourceBundle.getBundle("database");
    String url = resource.getString("db.host");
    String user = resource.getString("db.user");
    String pass = resource.getString("db.password");

    return DriverManager.getConnection(url, user, pass);
  }

  /*
  Можно было избавиться от дубликатов с помощью Java Stream distinct()
  но так нагляднее
   */
  public static Set<String> cleanDuplicates(Collection<String> collection) {
    Set<String> elements = new TreeSet<>();
    for (String element : collection) {
      elements.add(element);
    }
    return elements;
  }

}