import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LinkGetterWithFJPool extends RecursiveTask<List<Element>> {

  LinkedList<Element> listURL = new LinkedList<>();

  public LinkGetterWithFJPool(LinkedList<Element> listURL) {
    this.listURL = listURL;
  }

  @Override
  protected List<Element> compute() {

    Document docNeed = null;
    try {
      docNeed = Jsoup
          .connect(Main.URL_SKILLBOX)
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
      listURL.add(docs); // прибавляем найденные элементы в список
      return listURL;
    }




    return null;
  }
}
