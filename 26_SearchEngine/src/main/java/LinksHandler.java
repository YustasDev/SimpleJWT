import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LinksHandler {

  public static Set<String> uniqueURL = new HashSet<String>();
  public static String original_site = "http://www.playback.ru/";

  public static void main(String[] args) {
    LinksHandler obj = new LinksHandler();
    obj.get_links(original_site);
  }
  private void get_links(String url) {
    try {
      //Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
      Document doc = Jsoup
            .connect(url)
            .userAgent("Mozilla/5.0")
            .referrer("http://www.google.com")
            .timeout(10 * 1000)
            .get();

      Elements links = doc.select("a");
      if (links.isEmpty()) {
        return;
      }
      links.stream().map((link) -> link.attr("abs:href")).forEachOrdered((this_url)
          -> {
        boolean add = uniqueURL.add(this_url);
        if (add && this_url.contains(original_site)) {
          System.out.println(this_url);
          get_links(this_url);
        }
      });
    } catch (IOException ex) {
    }
  }
}
