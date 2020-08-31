import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

  private static final String URL_NEED = "https://www.moscowmap.ru/metro.html#lines";
  private static final File OUTPUT_FILE = new File(
      "c:/Users/Yustas/java_basics/09_FilesAndNetwork/MetroParser/data/metro.json");

  public static void main(String[] args) throws Exception {
    // парсим страницу «Список станций Московского метрополитена» и
    //записываем на диск JSON-файл со списком станций по линиям и списком линий
    // по формату JSON-файла из проекта SPBMetro
    Converter.fromPrototypeMetroToJSON(parsingMetroToPrototype(URL_NEED));

    // Читаем файл и выводим в консоль количество станций на каждой линии
    Converter.printCountingStations(OUTPUT_FILE);
  }


  private static PrototypeMetro parsingMetroToPrototype(String oneUrl) {

    Document docMetro = null;
    try {           // используя jsoup создаем объект Document содержащий код страницы по указанному URL
      docMetro = Jsoup
          .connect(oneUrl)
          .maxBodySize(0)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Ошибка при парсинге страницы");
      System.exit(13);
    }
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
    // возвращаем объект по форме соответствующий формату JSON-файла из проекта SPBMetro
    return new PrototypeMetro(stations, lines);
  }
}

