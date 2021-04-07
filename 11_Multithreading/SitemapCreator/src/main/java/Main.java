import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
  private static String recordedFile = "output.txt";

  public static void main(String[] args) {

    List<String> resultList = new ForkJoinPool()
        .invoke(new LinkGetterWithFJPool(URL_NEED));
    System.out.println("Все найденные URL: " + resultList);
    Set<String> nonDuplicates = findDuplicates(resultList);
    System.out.println("Без дубликатов:");
    nonDuplicates.forEach((e) -> { System.out.println(e); });

    System.out.println("Отсортированный поток стартовал");
    try(FileOutputStream fos = new FileOutputStream(recordedFile);
        PrintStream printStream = new PrintStream(fos))
    {
      nonDuplicates.stream().sorted(Comparator.naturalOrder())
          .forEach(e -> printStream.println(e));
    }
    catch(IOException ex){
      System.out.println(ex.getMessage());
    }

    System.out.println("Запись в файл произведена");
  }

  public static Set<String> findDuplicates(Collection<String> collection) {
    Set<String> elements = new TreeSet<>();
    return collection.stream()
        .filter(e -> elements.add(e))
        .collect(Collectors.toSet());
  }
}

