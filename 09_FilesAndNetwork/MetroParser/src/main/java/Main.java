import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {


  private static final String URL_NEED = "https://www.moscowmap.ru/metro.html#lines";
  private static final File JSON_FILE = new File(
      "c:/Users/Yustas/java_basics/09_FilesAndNetwork/MetroParser/data/metro.json");

  public static void main(String[] args) throws Exception {

    Converter.fromMetroLineAndStationToJSON(parsingMetroLineAndStations(URL_NEED));
   // Converter.toJSON(parsingMetroToPrototype(URL_NEED));
    //Converter.countingStations(Converter.jsonReader(JSON_FILE));
    //System.out.println(parsingMetroLineAndStations(URL_NEED));
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

    List<MetroLineAndStations> lines = elementDocMetro.select("span.js-metro-line")
        .stream()
        .map(el -> {
          String lineNo = el.attributes().get("data-line");

          List<MetroStation> stations = el.parent().parent().select(
              "div[data-depend-set=lines-" + lineNo
                  + "]>div.js-metro-stations.t-metrostation-list-table>p>a")
              .stream().map(stationEl -> new MetroStation(stationEl.child(1).text())).collect(Collectors.toList());
          return new MetroLineAndStations(lineNo, el.text(), stations);
        })
        .collect(Collectors.toList());
    return lines;

    
  }


//  private static List<PrototypeMetro> parsingMetroToPrototype(String oneUrl) {
//
//    Document docMetro = null;
//    try {           // используя jsoup создаем объект Document содержащий код страницы по указанному URL
//      docMetro = Jsoup
//          .connect(oneUrl)
//          .maxBodySize(0)
//          .userAgent("Mozilla/5.0")
//          .timeout(10 * 1000)
//          .get();
//    } catch (IOException e) {
//      e.printStackTrace();
//      System.out.println("Ошибка при парсинге страницы");
//      System.exit(13);
//    }
//
//        List<PrototypeMetro> prototype = docMetro.select("[data-line]")
//        .stream()
//        .map(el -> {
//
////          List<MetroStation> metroStationsList = el.select(".js-metro-stations")
////              .stream().map(stationEl -> new MetroStation(stationEl.child(1).text())).collect(Collectors.toList());
////
//          List<MetroLine> metroLines = el.select(".js-metro-line")
//              .stream().map(ml -> new MetroLine(ml.attributes().get("data-line"), ml.text()))
//              .collect(Collectors.toList());
//
//          List<MetroStation> list = new ArrayList<>();
//          for (Element stationEl : el.select(".js-metro-station")) {
//            MetroStation metroStation = new MetroStation(stationEl.child(1).text());
//            list.add(metroStation);
//          }
//          Map<String, List<MetroStation>> stations =
//
//          return new PrototypeMetro(stations, metroLines);
//        })
//        .collect(Collectors.toList());
//    return prototype;
 // }




//  private static List<MetroLine> parsingMetroToList(String oneUrl) {
//
//    Document docMetro = null;
//    try {           // используя jsoup создаем объект Document содержащий код страницы по указанному URL
//      docMetro = Jsoup
//          .connect(oneUrl)
//          .maxBodySize(0)
//          .userAgent("Mozilla/5.0")
//          .timeout(10 * 1000)
//          .get();
//    } catch (IOException e) {
//      e.printStackTrace();
//      System.out.println("Ошибка при парсинге страницы");
//      System.exit(13);
//    }
//
//    Elements elementDocMetro = docMetro
//        .select("#metrodata"); // работает и без этого, но сокращает время поиска
//
//    // парсим номера и названия линий, номера и названия станций, записываем в список объектов <MetroLine>
//    List<MetroLine> lines = elementDocMetro.select("span.js-metro-line")
//        .stream()
//        .map(el -> {
//          String lineNo = el.attributes().get("data-line");
//          List<MetroStation> stations = el.parent().parent().select(
//              "div[data-depend-set=lines-" + lineNo
//                  + "]>div.js-metro-stations.t-metrostation-list-table>p>a")
//              .stream().map(stationEl -> new MetroStation(stationEl.child(0).text(),
//                  stationEl.child(1).text())).collect(Collectors.toList());
//          return new MetroLine(lineNo, el.text(), stations);
//        })
//        .collect(Collectors.toList());
//    return lines;
//  }
}








