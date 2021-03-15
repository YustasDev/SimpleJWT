import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LinkGetterWithFJPool extends RecursiveTask<LinkedList<String>> {

  private static final Logger LOGGER = LogManager.getLogger(LinkGetterWithFJPool.class);
  private static final Marker HISTORY_PARSING = MarkerManager.getMarker("HISTORY_PARSING");
  LinkedList<String> listURLtext = new LinkedList<>();
  LinkedList<String> listURLSelect = new LinkedList<>();
  Set<String> visitedLinks = new HashSet<>();
  LinkedList<String> finalList = new LinkedList<>();
  String outputFileName = "file.txt";
  String url;

  public LinkGetterWithFJPool(String url) {
    this.url = url;
  }

  @Override
  protected LinkedList<String> compute() {

    Document docNeed = null;
    try {
      docNeed = Jsoup
          .connect(url)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
      LOGGER.error("Parsing failed {} ", e);
      throw new DuringParseException("Ошибка при парсинге страницы  ", url);
    }

    for (Element docs : docNeed  // в коде страницы
        .select("a[href]"))  // осуществляем поиск элементов соответствующих требованию
    {
      listURLtext.add(docs.attr("abs:href")); // прибавляем найденные элементы в список
    }

    for (String select : listURLtext) {
      if (select.startsWith("https://Skillbox.ru/") && !select.endsWith("pdf") && !visitedLinks.contains(select)) {
          listURLSelect.add(select);
          LOGGER.info(HISTORY_PARSING, " listURLSelect is {} ", listURLSelect);
        }
      visitedLinks.add(select);
      }


    List<LinkGetterWithFJPool> taskList = new ArrayList<>();
    for (String url : listURLSelect) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      LinkGetterWithFJPool task = new LinkGetterWithFJPool(url);
      task.fork();
      taskList.add(task);
    }

    for (LinkGetterWithFJPool task : taskList) {
      finalList.addAll(task.join());

//      try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
//          writter.write(join + "\n");
//      }
//      catch (IOException e) {
//        e.printStackTrace();
//      }
    }
    return finalList;
  }
}
