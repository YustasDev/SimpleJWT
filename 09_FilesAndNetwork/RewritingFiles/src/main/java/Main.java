import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

  private static Scanner scanner;

  public static void main(String[] args) throws IOException {

    for (; ; ) {
      scanner = new Scanner(System.in);

      String sourse = receivePath(
          "Введите путь к папке, которую надо переписать. Образец: С:/Games/Star Wars");
      String dest = receivePath(
          "Введите путь, куда надо переписать. Образец: D:/Archive/Games");

      Path sourcePath = Paths.get(sourse);
      Path destinationPath = Paths.get(dest);
      copyFolder(sourcePath, destinationPath, dest);
    }
  }

  private static String receivePath(String message) {

    for (; ; ) {
      System.out.println(message);
      String line = scanner.nextLine().trim();
      if (line != null) {
        return line;
      }
    }
  }


  private static void copyFolder(Path source, Path target, String dest) throws IOException {
    try (Stream<Path> stream = Files.walk(source)) {
      stream.forEach((child) -> {
        try {
          Path relative = source.relativize(child);
          Path destination = target.resolve(relative);
          new File(String.valueOf(dest)).mkdirs();
          if (!destination.toFile().isDirectory()) {
            Files.copy(child, destination, StandardCopyOption.REPLACE_EXISTING);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
    }
  }
}

