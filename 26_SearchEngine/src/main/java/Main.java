import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

import models.Lemma;
import models.MyIndex;
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

import javax.persistence.NoResultException;
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
      int idPage = page.getId();
      StringBuilder sbContent = new StringBuilder();
      List<String> contentList = new ArrayList<>();
      String originalContent = page.getContent();
      String cleanContent = Jsoup.clean(originalContent, Whitelist.none());

      cleanContent = cleanContent.replaceAll("[^А-Яа-я \\pP-]", "").replaceAll("\\sр\\s", "")
              .replaceAll("\\sГБ\\s", "").replaceAll("[\\p{P}&&[^\\-]]", " ");

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

      Document docTitle = Jsoup.parse(originalContent);
      String textInTitle = docTitle.select("title").text();

      StringBuffer textBody = new StringBuffer();
      for (Element result : docTitle.select("body")) {
        String text = result.text();
        textBody.append(text);
      }
      String textInBody = String.valueOf(textBody);

      String strInTitle = Jsoup.clean(textInTitle, Whitelist.none())
              .replaceAll("[^А-Яа-я -]", "").replaceAll("\\sр\\s", " ").replaceAll("\\sГБ\\s", " ");
      String strInBody = Jsoup.clean(textInBody, Whitelist.none())
              .replaceAll("[^А-Яа-я -]", "").replaceAll("\\sр\\s", " ").replaceAll("\\sГБ\\s", " ");


      Map<String, Integer> lemmsInTitle = new HashMap<>();
      Map<String, Integer> lemmsInBody = new HashMap<>();
      Map<String, Double> rankOflemms = new HashMap<>();
      try {
        lemmsInTitle =  Morphology.getSetLemmas(strInTitle);
        System.out.println("lemmsInTitle = " + lemmsMapFromPage);
        LOGGER.info(HISTORY_PARSING, "Parsing of the tags <title> the page:  " + page.getPath() + " was performed successfully");
      } catch (IOException e) {
        LOGGER.error("Error when parsing of the tags <title> the page: " + page.getPath() + "for getting lemmas");
        e.printStackTrace();
      }

      try {
        lemmsInBody =  Morphology.getSetLemmas(strInBody);
        System.out.println("lemmsInBody = " + lemmsMapFromPage);
        LOGGER.info(HISTORY_PARSING, "Parsing of the tags <body> the page:  " + page.getPath() + " was performed successfully");
      } catch (IOException e) {
        LOGGER.error("Error when parsing of the tags <body> the page: " + page.getPath() + "for getting lemmas");
        e.printStackTrace();
      }
//================== TODO  fill in table myIndex
      final Double rankOfLemmaInTitle = 1.0;  // coefficient for the field "title"
      final Double rankOfLemmaInBody = 0.8;   // coefficient for the field "body"
      lemmsInTitle.forEach((key, value) -> {
      String lemmaInTitle = key;
      Double rankInTitle = Double.valueOf(value);
  //    if(rankOflemms.containsKey(lemmaInTitle)) {
        rankOflemms.put(lemmaInTitle, rankInTitle);
//      }
//      else {
//        rankOflemms.put(lemmaInTitle, rankOfLemmaInTitle);
//      }
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

//================= TODO

                  int x =13;











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