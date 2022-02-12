import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

  public static final String URL_NEED = "http://www.playback.ru/";
  private static String recordedFile = "output.txt";

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
      SQLQuery insertQuery = session.createSQLQuery("" +
          "INSERT INTO page(path, code, content)VALUES(?,?,?)");
      insertQuery.setParameter(1, currentPage.getPath());
      insertQuery.setParameter(2, currentPage.getCode());
      insertQuery.setParameter(3, currentPage.getContent());
      insertQuery.executeUpdate();
      session.getTransaction().commit();
    }

    session.close();
    sessionFactory.close();







//    try {
//      Connection connection = getConnection();
//      String query = " insert into page (path, code, content)"  + " values (?, ?, ?)";
//      PreparedStatement preparedStmt = connection.prepareStatement(query);
//
//      for (String url : nonDuplicates) {
//        Page currentPage = (Page) LinkGetterWithFJPool.htmlStore.getOrDefault(url, "No data available");
//        preparedStmt.setString (1, currentPage.getPath());
//        preparedStmt.setInt(2, currentPage.getCode());
//        preparedStmt.setString (3, currentPage.getContent());
//        preparedStmt.execute();
//      }
//
//      preparedStmt.close();
//      connection.close();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }


//    System.out.println("Сортировка коллекции стартовала");
//    try (PrintStream printStream = new PrintStream(new File(recordedFile))) {
//      nonDuplicates.stream().sorted(Comparator.naturalOrder())
//          .forEach(url ->
//              {
//                String[] data = url.split("\\/");
//                int count = data.length;
//
//                printStream.println(IntStream.range(0, count).boxed().map((i) -> "\t")
//                    .collect(Collectors.joining()) + url);
//              }
//          );
//    } catch (IOException ex) {
//      System.out.println(ex.getMessage());
//    }
//    System.out.println("Запись в файл произведена");
    //System.out.println(LinkGetterWithFJPool.htmlStore);
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