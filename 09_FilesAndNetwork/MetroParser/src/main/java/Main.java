import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

  private static final String URL_NEED = "https://www.moscowmap.ru/metro.html#lines";
  public static final File OUTPUT_FILE = new File(
      "c:/Users/Yustas/java_basics/09_FilesAndNetwork/MetroParser/data/metro.json");

  public static void main(String[] args) throws Exception {
    // парсим страницу «Список станций Московского метрополитеsaна» и
    //записываем на диск JSON-файл со списком станций по линиям и списком линий
    // по формату JSON-файла из проекта SPBMetro
    for (int i = 0; i<5; i++)
    try {
      Converter.fromPrototypeMetroToJSON(parsingMetroToPrototype(URL_NEED));
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("Ошибка при парсинге страницы");
      Thread.sleep(2000);
      if (i>= 5) {
      System.out.println("Критическая ошибка, дальнейшая работа невозможна");
      return;
      }
    }

    // Читаем файл и выводим в консоль количество станций на каждой линии
//    for (Map.Entry entry : Converter.returnCountingStations(OUTPUT_FILE).entrySet()) {
//      System.out.println(entry.getKey() + "  содержит: " + entry.getValue() + " станций");
//    }
  }


  private static PrototypeMetro parsingMetroToPrototype(String oneUrl) throws Exception {

    Document docMetro = null;
     // используя jsoup создаем объект Document содержащий код страницы по указанному URL
      docMetro = Jsoup
          .connect(oneUrl)
          .maxBodySize(0)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();

    // можно и без этого, но так выборка из кода страницы осуществляется бустрее
    Elements metrodata = docMetro.select("#metrodata");

    // получаем список линий (номер +название)
    List<MetroLine> lines = metrodata.select("span.js-metro-line")
        .stream()
        .map(e -> new MetroLine(e.attr("data-line"), e.text()))
        .collect(Collectors.toList());

    // получаем Мар (номер линии + станции)
    Map<String, List<String>> stations = new LinkedHashMap<>(); // LinkedHashMap чтобы сохранить порядок вставки

    for (MetroLine line : lines) {
      List<String> lineStations = metrodata.select(String
          .format("div.js-metro-stations[data-line='%s'] span.name", line.getNumber()))
          .stream()
          .map(Element::text)
          .collect(Collectors.toList());
      stations.put(line.getNumber(), lineStations);
    }

    Set<List<Station>> connections = new LinkedHashSet<>();
    for (MetroLine line : lines) {
      List<Station> transitionStations = new ArrayList<>();
      metrodata.select("div.js-metro-stations p span.name").stream()
          .map(st -> st.select(".js-metro-stations span.t-icon-metroln"))

        //System.out.println(transitionStations);
      });
    }
    // возвращаем объект по форме соответствующий формату JSON-файла из проекта SPBMetro
    return new PrototypeMetro(stations, connections, lines);
  }
}

