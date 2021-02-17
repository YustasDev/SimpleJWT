import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main {

  private static final String URL_NEED = "https://lenta.ru/";

  public static void main(String[] args) {

    Document docLenta = null;
    try {
      docLenta = Jsoup
          .connect(URL_NEED)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Ошибка при парсинге страницы");
      return;
    }

    LinkedList<String> listURL = new LinkedList<>();

    for (Element docs : docLenta  // в коде страницы
        .select("<a href="))  // осуществляем поиск элементов соответствующих требованию
    {
     // listURL.add(docs.attr("abs:src")); // прибавляем найденные элементы в список
    }







  }
}
