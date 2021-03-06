import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class LinkGetterWithFJPool extends RecursiveTask<List<Element>> {

  LinkedList<Element> listURL = new LinkedList<>();
  LinkedList<String> listURLtext = new LinkedList<>();
  LinkedList<String> listURLSelect = new LinkedList<>();
  List<LinkGetterWithFJPool> taskList = new LinkedList<>();


  public LinkGetterWithFJPool(LinkedList<Element> listURL) {
    this.listURL = listURL;
  }

  @Override
  protected List<Element> compute() {
    Document docNeed = null;
    try {
      docNeed = Jsoup
          .connect(String.valueOf(Main.URL_SKILLBOX))
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

    for (String link : listURLSelect) {
      Document doc = Jsoup.parse(link);
      listURL.add(doc);
    }


    for (Element url : listURL) {
      
    }


    return listURL;
  }
}
