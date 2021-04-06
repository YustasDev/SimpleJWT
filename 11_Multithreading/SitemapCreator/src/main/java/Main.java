import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class Main {
  
  public static final String URL_NEED = "http://sendel.ru/";
  public static final String URL_SKILLBOX = "https://Skillbox.ru/";

  public static void main(String[] args) {


    List<String> resultList = new ForkJoinPool()
        .invoke(new LinkGetterWithFJPool(URL_NEED));
    System.out.println("Все найденные URL: " + resultList);
    Set<String> nonDuplicates = findDuplicates(resultList);
    System.out.println("Без дубликатов:");
    nonDuplicates.forEach((e) -> { System.out.println(e); });

  }

  public static Set<String> findDuplicates(Collection<String> collection) {
    Set<String> elements = new HashSet<>();
    return collection.stream()
        .filter(e -> elements.add(e))
        .collect(Collectors.toSet());
  }
}

