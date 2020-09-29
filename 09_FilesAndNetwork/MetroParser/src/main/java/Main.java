import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
  static final ObjectMapper mapper = new ObjectMapper();
  static final Pattern TITLE_PATTERN = Pattern.compile("^переход на станцию «(.*)».*$");


  public static void main(String[] args) throws Exception {
    // парсим страницу «Список станций Московского метрополитеsaна»

    int i = 0;
    PrototypeMetro metro = null;
    
    while (true) {
      try {
        metro = parsingMetroToPrototype(URL_NEED);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Ошибка при парсинге страницы");
        Thread.sleep(2000);
        i++;
        if (i >= 5) {
          System.out.println("Критическая ошибка, дальнейшая работа невозможна");
          return;
        }
      }

      //записываем на диск JSON-файл со списком станций, линий и переходов
      // по формату JSON-файла из проекта SPBMetro
      File fileMM = Converter.fromPrototypeMetroToJSON(metro);
      if (fileMM.exists()) {
        System.out.println("json created!");
      } else {
        System.out.println("Ошибка при создании файла *.json");
        return;
      }

      // Читаем файл и выводим в консоль количество станций на каждой линии
      for (Map.Entry entry : Converter.returnCountingStations(OUTPUT_FILE).entrySet()) {
        System.out.println(entry.getKey() + "  содержит: " + entry.getValue() + " станций");
      }
      System.out.println();

      PrototypeMetro metroFromFile = Converter.jsonReaderFromFile(OUTPUT_FILE);
      for (List<Connections> connection : metroFromFile.connections) {
        Connections left = connection.get(0);
        Connections right = connection.get(1);
        MetroLine leftLine = findLine(metroFromFile, left.getLineNo());
        MetroLine rightLine = findLine(metroFromFile, right.getLineNo());
        System.out.printf("Переход c линии %s.'%s' станция '%s' на линию %s.'%s' станцию '%s'%n",
            leftLine.getNumber(), leftLine.getName(), left.getTransferStation(), rightLine.getNumber(), rightLine.getName(),
            right.getTransferStation());
      }
      System.out.println("Всего в Московском метро: " + metroFromFile.connections.size() + " переходов между станциями.");
    return;
  }
  }

  private static MetroLine findLine(PrototypeMetro metro, String lineNo) {
    return metro.getLines().stream()
        .filter(l -> l.getNumber().equals(lineNo))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Can't find line - specified lineNo=" + lineNo));
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

    // можно и без этого, но так выборка из кода страницы осуществляется быстрее
    Elements metrodata = docMetro.select("#metrodata");

    // получаем список линий (номер +название)
    List<MetroLine> lines = metrodata.select("span.js-metro-line")
        .stream()
        .map(e -> new MetroLine(e.attr("data-line"), e.text()))
        .collect(Collectors.toList());
    // используем LinkedHashMap и LinkedHashSet, чтобы сохранить порядок вставки
    Map<String, List<String>> stations = new LinkedHashMap<>();
    Set<List<Connections>> connections = new LinkedHashSet<>();

    //обходим линии, в линиях станции
    for (MetroLine line : lines) {
      List<String> lineStations = new ArrayList<>();
      Elements stationsList = metrodata
          .select(String.format("div.js-metro-stations[data-line='%s'] a", line.getNumber()));
      for (Element stationNode : stationsList) {
        String stationName = stationNode.select("span.name").text();
        lineStations.add(stationName);
        // получаем список переходов (это тэги спан начиная с 3-го)
        // если переходов нет то список пустой
        Elements transitionNodes = stationNode.select("span:nth-child(n+3)");
        for (Element transitionNode : transitionNodes) {
          // номер линии на которую осуществляется переход закодирован в присвоенном CSS стиле
          // нужно найти имя стиля начинающего на ln- среди прочих и вычленить из него
          // номер станции
          String transitionToLineNo = transitionNode.classNames().stream()
              .filter(cn -> cn.startsWith("ln-"))
              .map(s -> s.substring(3))
              .findFirst()
              .orElseThrow();

          String title = transitionNode.attr("title");
          // С помощью регулярного выраженния получим имя станции из значения атрибута title
          String transitionToStationName = extractStationFromTitle(title)
              .orElseThrow();

          // отфильтруем пересадки, чтобы одна и таже пересадка не повторялась дважды
          Connections part1 = new Connections(line.getNumber(), stationName);
          Connections part2 = new Connections(transitionToLineNo, transitionToStationName);
          if (!(connections.contains(part1) || connections.contains(part2))) {
          connections.add(List.of(part1, part2));}
        }
      }
      stations.put(line.getNumber(), lineStations);
    }
    // возвращаем объект по форме соответствующий формату JSON-файла из проекта SPBMetro
    return new PrototypeMetro(stations, connections, lines);
  }

  static Optional<String> extractStationFromTitle(String title) {
    Matcher matcher = TITLE_PATTERN.matcher(title);
    // Get the group matched using group() method
    return matcher.find() ? Optional.of(matcher.group(1)) : Optional.empty();
  }
}

