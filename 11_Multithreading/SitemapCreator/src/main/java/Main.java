import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class Main {

  public static final String URL_NEED = "https://lenta.ru/";
  public static final String URL_SKILLBOX = "https://Skillbox.ru/";
  public static LinkedList<Element> listURL = new LinkedList<>();

  public static void main(String[] args) {

//    LinkGetter linkGetter = new LinkGetter();
//    List needUrlList = linkGetter.getLinks(URL_NEED);
//
//    for (Object s : needUrlList) {
//          System.out.println(s);
//        }

    // ===============================================================




        String fgp = new ForkJoinPool().invoke(new LinkGetterWithFJPool(URL_SKILLBOX));
        System.out.println(fgp);


      }
    }

