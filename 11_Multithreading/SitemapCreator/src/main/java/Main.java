import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


public class Main {

  public static final String URL_NEED = "http://sendel.ru/";
  private static String recordedFile = "output.txt";

  public static void main(String[] args) {

    List<String> resultList = new ForkJoinPool()
        .invoke(new LinkGetterWithFJPool(URL_NEED));
    System.out.println("Все найденные URL: " + resultList);
    Set<String> nonDuplicates = cleanDuplicatesAndInternalElements(resultList);
    System.out.println("Без дубликатов и внутренних элементов:");
    nonDuplicates.forEach((e) -> {
      System.out.println(e);
    });

    System.out.println("Сортировка коллекции стартовала");
    try (FileOutputStream fos = new FileOutputStream(recordedFile);
        PrintStream printStream = new PrintStream(fos)) {
      nonDuplicates.stream().sorted(Comparator.naturalOrder())
          .forEach(e -> printStream.println(e));
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }

    System.out.println("Запись в файл произведена");
  }

  public static Set<String> cleanDuplicatesAndInternalElements(Collection<String> collection) {
    Set<String> elements = new TreeSet<>();
    for (String element : collection) {
      if (element.contains("#")) {
        int indexOfNeedless = element.indexOf("#");
        element = element.substring(0, indexOfNeedless);
      }
      elements.add(element);
    }
    return elements;
  }
}

