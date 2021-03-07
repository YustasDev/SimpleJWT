import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LinkGetterWithFJPool extends RecursiveTask<LinkedList<String>> {

  LinkedList<String> listURLtext = new LinkedList<>();
  LinkedList<String> listURLSelect = new LinkedList<>();
  LinkedList <String> joinList = new LinkedList<>();
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
          .connect(String.valueOf(url))
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get(); }
    catch (IOException e) {
      e.printStackTrace();
      System.out.println("Ошибка при парсинге страницы");
    }

    for (Element docs : docNeed  // в коде страницы
        .select("a[href]"))  // осуществляем поиск элементов соответствующих требованию
    {
      listURLtext.add(docs.attr("abs:href")); // прибавляем найденные элементы в список
    }

    for (String select : listURLtext) {
      if (select.startsWith("https://Skillbox.ru/") && !select.endsWith("pdf")) {
        listURLSelect.add(select);
      }
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
      LinkedList<String> joinList = task.join();
//      try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
//          writter.write(join + "\n");
//      }
//      catch (IOException e) {
//        e.printStackTrace();
//      }
    }
    return joinList;
  }
}
