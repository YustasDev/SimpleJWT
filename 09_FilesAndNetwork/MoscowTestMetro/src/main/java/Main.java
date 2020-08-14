import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static Map<String, Object> metro = new HashMap<>();                 // Общая коллекция
    public static Map<String, List<String>> stations = new HashMap<>();        // Коллекция станций
    public static Set<String> lineNumbers = new HashSet<String>();             // Коллекция номеров линий


    private static final String URL_NEED = "https://www.moscowmap.ru/metro.html#lines";
    private static final String JSON_DESTINATION_FOLDER = "c:/Users/Yustas/java_basics/09_FilesAndNetwork/MetroParser/data/metro.json";

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

      ArrayList<Element> listMetroData = new ArrayList<>();

      Elements element = docMetro.select("#metrodata");
      //String safe = Jsoup.clean(String.valueOf(element), Whitelist.basic());

      //парсинг  номеров линий
//      element.stream().filter(tr -> !tr.select("span.js-metro-line").text().isEmpty())
//          .forEach(tr -> lineNumbers.add(tr.select("span.js-metro-line").text()));

//      element.stream().filter(tr -> !tr.select("div.js-metro-stations").text().isEmpty())
//          .forEach(tr -> lineNumbers.add(tr.select("div.js-metro-stations").text()));

//      element.stream().filter(tr -> !tr.select("div.js-toggle-depend").isEmpty())
//          .forEach(tr -> SetNumbers.add(tr.select("div.js-toggle-depend")));

      HashSet<Elements> setNumbers = new HashSet<>();

      element.stream().filter(tr -> !tr.select("span.js-metro-line").isEmpty())
          .forEach(tr -> setNumbers.add(tr.select("span.js-metro-line")));

      setNumbers.stream().forEach(System.out::println);
      System.out.println(setNumbers.size());

      ArrayList<String> listNumberN = new ArrayList<>();

      for (Elements e : setNumbers)
      {
       listNumberN.add(e.attr("data-line"));
       String needN = e.attr("data-line");
       System.out.println(needN);
      }
        listNumberN.stream().forEach(System.out::println);


//      element.stream().filter(tr -> !tr.select("[data-line]").text().isEmpty())
//          .forEach(tr -> lineNumbers.add(tr.select("[data-line]").text()));
    //  lineNumbers.stream().forEach(System.out::println);


}





      }





