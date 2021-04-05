import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class Main {

  //public static final String URL_NEED = "https://lenta.ru/";
  public static final String URL_NEED = "http://sendel.ru/";
  public static final String URL_SKILLBOX = "https://Skillbox.ru/";

  public static void main(String[] args) {

//    LinkGetter linkGetter = new LinkGetter();
//    List needUrlList = linkGetter.getLinks(URL_NEED);
//
//    for (Object s : needUrlList) {
//          System.out.println(s);
//        }
    // ===============================================================

    List<String> resultList = new ForkJoinPool()
        .invoke(new LinkGetterWithFJPool(URL_NEED));
    System.out.println(resultList);


  }
}

