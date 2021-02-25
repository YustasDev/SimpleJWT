import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main {

  private static final String URL_NEED = "https://lenta.ru/";

  public static void main(String[] args) {

    LinkGetter linkGetter = new LinkGetter();
    List needUrlList = linkGetter.getLinks(URL_NEED);

    for (Object s : needUrlList) {
          System.out.println(s);
        }






//    Document docLenta = null;
//    try {
//      docLenta = Jsoup
//          .connect(URL_NEED)
//          .userAgent("Mozilla/5.0")
//          .timeout(10 * 1000)
//          .get();
//    } catch (IOException e) {
//      e.printStackTrace();
//      System.out.println("Ошибка при парсинге страницы");
//      return;
//    }
//
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
