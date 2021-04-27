import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

//    public static Map<String, Object> metro = new HashMap<>();                 // Общая коллекция
//    public static Map<String, List<String>> stations = new HashMap<>();        // Коллекция станций
//    public static Set<String> lineNumbers = new HashSet<String>();             // Коллекция номеров линий


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



        List<MetroLine> lines = new ArrayList<>();
        List<String> stations = new ArrayList<>();

      List<String> finalStations = stations;
      //Map<MetroLine, List<String>> inventoryMetro =
          element.select("data-line").forEach(el -> {
            String name = el.select("span.js-metro-line").text();
            String number = el.attributes().get("data-line");
            finalStations.add(el.select("div.js-metro-stations").text());
            lines.add(new MetroLine(number, name, finalStations));
          });


//
//            collect(Collectors.groupingBy(MetroLine::getName, TreeMap::new,
//            Collectors.toList(stations = el
//


          lines.forEach(System.out::println);
          //stations.forEach(System.out::println);
      }

    }




//
//
//      HashSet<Element> setNumbers = new HashSet<>();
//
//      element.select("span.js-metro-line").forEach(setNumbers::add);
//
//     // element.stream().filter(tr -> !tr.select("span.js-metro-line").isEmpty())
//     //     .forEach(tr -> setNumbers.add(tr.select("span.js-metro-line")));
//
//      setNumbers.stream().forEach(System.out::println);
//      System.out.println(setNumbers.size());
//
//      ArrayList<String> listNumberN = new ArrayList<>();
//
//      for (Element e : setNumbers)
//      {
//      // listNumberN.add(e.attr("data-line"));
//       String needN = e.attr("data-line");
//       System.out.println(needN);
//      }
       // listNumberN.stream().forEach(System.out::println);


//      element.stream().filter(tr -> !tr.select("[data-line]").text().isEmpty())
//          .forEach(tr -> lineNumbers.add(tr.select("[data-line]").text()));
    //  lineNumbers.stream().forEach(System.out::println);

//
//}
//
//
//
//
//
//      }





