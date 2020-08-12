import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

  private static final String URL_NEED = "https://www.moscowmap.ru/metro.html#lines";

  public static void main(String[] args) {

    Document docMetro = null;
    try {           // используя jsoup создаем объект Document содержащий код страницы по указанному URL
      docMetro = Jsoup
          .connect(URL_NEED)
          .maxBodySize(0)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Ошибка при парсинге страницы");
      System.exit(13);
    }

    ArrayList<String> listMetroData = new ArrayList<>();

    for (Element docs : docMetro
        .select("metrodata")) {
      listMetroData.add(String.valueOf(docs));
    }

    for (String e : listMetroData) {
      System.out.println(e);
    }


  }


}
