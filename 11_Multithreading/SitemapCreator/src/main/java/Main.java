import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;


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
    List<Node> listUrlElement = new LinkedList<>();

    Pattern linkSkillbox = Pattern.compile("\"https://skillbox.ru/*\"");

    Document docNeed = null;
    try {
      docNeed = Jsoup
          .connect(Main.URL_SKILLBOX)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get(); }
    catch (IOException e) {
      e.printStackTrace();
      System.out.println("Ошибка при парсинге страницы");
    }

    for (Element docs : docNeed  // в коде страницы
        .select("a[href]"))  // осуществляем поиск элементов соответствующих требованию
    {
      listUrlElement = docs.childNodes();
      //listURL.add(docs.attr("abs:href")); // прибавляем найденные элементы в список
      //listURL.forEach(System.out::println);

//      listUrlElement = docs.childNodesCopy();
      listUrlElement.forEach(System.out::println);
    }


    for (Node node : listUrlElement)
    {
      listURL.add(node.attr("abs:href"));
      listURL.forEach(System.out::println);
    }

//    LinkedList<Element> listURL = new LinkedList<>();
//    LinkedList<String> listURLtext = new LinkedList<>();

//    for (Element docs : docLenta  // в коде страницы
//        .select("a[href]"))  // осуществляем поиск элементов соответствующих требованию
//    {
//      listURLtext.add(String.valueOf(docs)); // прибавляем найденные элементы в список
//      listURL.add(docs);
//    }
//
//    final String REGEX = "https://skillbox.ru/   ";
//
//      for (Element e : listURL) {
//        if (e.getElementsMatchingText("")) {
//          System.out.println(e);
//        }
//      }


    //System.out.println(listURL.stream().);



  }












}
