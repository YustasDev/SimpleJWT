import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    Elements elementDocMetro = docMetro
        .select("#metrodata"); // работает и без этого, но сокращает время поиска

    List<MetroLine> lines = elementDocMetro.select("span.js-metro-line")
        .stream()
        .map(el -> {
          String lineNo = el.attributes().get("data-line");
          List<MetroStation> stations = el.parent().parent().select(
              "div[data-depend-set=lines-" + lineNo
                  + "]>div.js-metro-stations.t-metrostation-list-table>p>a")
              .stream().map(stationEl -> new MetroStation(stationEl.child(0).text(),
                  stationEl.child(1).text())).collect(Collectors.toList());
          return new MetroLine(lineNo, el.text(), stations);
        })
        .collect(Collectors.toList());

    Converter.toJSON(lines);
    System.out.println(Converter.jsonReader(JSON_FILE));

  }

  private static void parsingMetroToList(String oneUrl) {


  }
}








