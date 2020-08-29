import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.select.Elements;


public class Main {


  private static final String URL_NEED = "https://www.moscowmap.ru/metro.html#lines";
  private static final File JSON_FILE = new File(
      "c:/Users/Yustas/java_basics/09_FilesAndNetwork/MetroParser/data/metro.json");

  public static void main(String[] args) throws Exception {

    //Converter.fromMetroLineAndStationToJSON(parsingMetroLineAndStations(URL_NEED));
    Converter.fromPrototypeMetroToJSON(parsingMetroToPrototype(URL_NEED));

  }

  private static List<MetroLineAndStations> parsingMetroLineAndStations(String oneUrl) {

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

    Elements elementDocMetro = docMetro
        .select("#metrodata"); // работает и без этого, но сокращает время поиска

    List<MetroLineAndStations> linesAndStations = elementDocMetro.select("span.js-metro-line")
        .stream()
        .map(el -> {
          String lineNo = el.attributes().get("data-line");

          List<MetroStation> onlyStations = el.parent().parent().select(
              "div[data-depend-set=lines-" + lineNo
                  + "]>div.js-metro-stations.t-metrostation-list-table>p>a")
              .stream().map(stationEl -> new MetroStation(stationEl.child(1).text()))
              .collect(Collectors.toList());

          return new MetroLineAndStations(lineNo, el.text(), onlyStations);
        })
        .collect(Collectors.toList());

    return linesAndStations;
  }


  private static HashMap<String, List<MetroStation>> formingMap(List<MetroLineAndStations> lmlas) {

    Map<String, List<MetroStation>> stationsStream = parsingMetroLineAndStations(URL_NEED).stream()
        .collect(
            Collectors.toMap(MetroLineAndStations::getNumber, MetroLineAndStations::getStations));
    return new HashMap<String, List<MetroStation>>(stationsStream);

  }

  private static List<MetroLine> numberAndDenominationLines(List<MetroLineAndStations> lmlas) {
    List<MetroLine> numberAndDenominationLines = new ArrayList<>();
    for (MetroLineAndStations elemList : parsingMetroLineAndStations(URL_NEED)) {
      MetroLine metroLine = new MetroLine(elemList.getNumber(), elemList.getName());
      numberAndDenominationLines.add(metroLine);
    }
    return numberAndDenominationLines;
  }

  private static List<PrototypeMetro> parsingMetroToPrototype(String oneUrl) {

    List<PrototypeMetro> prototype = parsingMetroLineAndStations(URL_NEED).stream()
        .map(elPrototype -> {

          Map<String, List<MetroStation>> stationsStream = parsingMetroLineAndStations(URL_NEED)
              .stream()
              .collect(
                  Collectors
                      .toMap(MetroLineAndStations::getNumber, MetroLineAndStations::getStations));

          List<MetroLine> numberAndDenominationLines = new ArrayList<>();
          for (MetroLineAndStations elemList : parsingMetroLineAndStations(URL_NEED)) {
            MetroLine metroLine = new MetroLine(elemList.getNumber(), elemList.getName());
            numberAndDenominationLines.add(metroLine);
          }
          return new PrototypeMetro(stationsStream, numberAndDenominationLines);
        })
        .collect(Collectors.toList());

    return prototype;

  }
}
