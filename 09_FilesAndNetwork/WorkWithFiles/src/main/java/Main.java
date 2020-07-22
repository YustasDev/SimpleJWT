import java.io.File;
import java.util.Scanner;
import java.util.stream.Stream;


public class Main {

  private static Scanner scanner;

  public static void main(String[] args) {

    scanner = new Scanner(System.in);

    String path = receivePath("Введите путь к папке. Образец: D:/Games");
    File folder = new File(path);
    File[] files = folder.listFiles();

    // Stream<File> streamArr = Stream.of(files);
    //streamArr.forEach(x -> System.out.println(x));

    Stream.of(files).forEach(x -> System.out.println(x));


  }

  private static String receivePath(String message) {

    for (; ; ) {
      System.out.println(message);
      String line = scanner.nextLine().trim();
      if (line != null) {
        return line;
      }
      System.out.println("Путь к папке (директории) не указан");
    }
  }
}