import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

  public static final String URL_NEED = "http://sendel.ru/";
  private static String recordedFile = "output.txt";

  public static void main(String[] args) {

    List<String> resultList = new ForkJoinPool()
        .invoke(new LinkGetterWithFJPool(URL_NEED));
    System.out.println("Все найденные URL: " + resultList);
    Set<String> nonDuplicates = cleanDuplicates(resultList);
    System.out.println("URL без дубликатов:");
    nonDuplicates.forEach((e) -> {
      System.out.println(e);
    });

    System.out.println("Сортировка коллекции стартовала");
    try (FileOutputStream fos = new FileOutputStream(recordedFile);
        PrintStream printStream = new PrintStream(fos)) {
      nonDuplicates.stream().sorted(Comparator.naturalOrder())
          .forEach(url ->
              {
                String[] data = url.split("\\/");
                int count = data.length;
                /*
                Если увидя следующую строку Вы захотите сжечь меня на костре, я прошу "Понять и простить"
                 */
                printStream.println(IntStream.range(0, count).boxed().map((i) -> "\t")
                    .collect(Collectors.joining()) + url);
              }
          );
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
    System.out.println("Запись в файл произведена");
  }

  /*
  Можно было избавиться от дубликатов с помощью Java Stream distinct()
  но так нагляднее
   */
  public static Set<String> cleanDuplicates(Collection<String> collection) {
    Set<String> elements = new TreeSet<>();
    for (String element : collection) {
      elements.add(element);
    }
    return elements;
  }
}