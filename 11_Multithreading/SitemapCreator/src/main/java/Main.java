import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class Main {

  public static final String URL_NEED = "https://lenta.ru/";
  public static final String URL_SKILLBOX = "https://Skillbox.ru/";

  public static void main(String[] args) {

//    LinkGetter linkGetter = new LinkGetter();
//    List needUrlList = linkGetter.getLinks(URL_NEED);
//
//    for (Object s : needUrlList) {
//          System.out.println(s);
//        }

    // ===============================================================

    LinkedList<String> listURL = new LinkedList<>();
    LinkedList<String> listURLSelect = new LinkedList<>();
    List<Node> listUrlElement = new LinkedList<>();

    Document docNeed = null;
    try {
      docNeed = Jsoup
          .connect(Main.URL_SKILLBOX)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Ошибка при парсинге страницы");
    }

    for (Element docs : docNeed  // в коде страницы
        .select("a[href]"))  // осуществляем поиск элементов соответствующих требованию
    {
      //listUrlElement = docs.childNodes();
      listURL.add(docs.attr("abs:href")); // прибавляем найденные элементы в список
     // listURL.forEach(System.out::println);
    }

    int count = 0;
    for (String select : listURL) {

      if (select.startsWith("https://Skillbox.ru/") && !select.endsWith("pdf")) {
        count++;
        listURLSelect.add(select);
      }


     // final String REGEX = "^[https://skillbox.ru/.*\\(^.pdf)]$";
//      Pattern p = Pattern.compile(REGEX);
//      Matcher m = p.matcher(select);
//      if (m.find()) {
//        count++;
//        listURLSelect.add(select);
      }

    listURLSelect.forEach(System.out::println);
    System.out.println("Найдено " + count + " ссылок");


//      String cssQuery = "a[href*=/skillbox.ru/]";
//      Elements elements= docNeed.select(cssQuery);
//
//      Iterator<Element> iterator = elements.iterator();
//
//      while(iterator.hasNext())  {
//        Element e = iterator.next();
//        System.out.println(e.attr("href"));
      }

    }

