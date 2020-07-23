import java.io.File;
import java.util.Scanner;
import java.util.stream.Stream;


public class Main {

  private static Scanner scanner;

  public static void main(String[] args) {

   for (; ;) {
     scanner = new Scanner(System.in);
     mark1:
     {
       String path = receivePath("Введите путь к папке. Образец: D:/Games");
       File folder = new File(path);

       File[] files;
       if (folder.isDirectory()) {
         files = folder.listFiles();
       } else {
         System.out.println("Указан неправильный путь к папке");
         break mark1;
       }

       //List<Long> collect =
           Stream.of(files).map(x -> x.getTotalSpace()).forEach(x -> System.out.println(x));
       Stream.of(files).forEach(x -> System.out.println(x));
         //  collect(Collectors.toList());
      // System.out.println(collect);

     }
   }
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