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
          "Введите путь к папке, которую надо копировать. Образец: С:/Games/StarWars");
      String dest = receivePath(
          "Введите путь, куда надо переписать. Образец: D:/Archive/Games");

      Path sourcePath = Paths.get(sourse);
      Path destinationPath = Paths.get(dest);
      copyFolder(sourcePath, destinationPath);
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

  private static void copyFolder(Path source, Path target) {
    Boolean check = true;
    try (Stream<Path> stream = Files.walk(source)) {
      stream.forEach((child) -> {
        try {
          Path relative = source.relativize(child);
          Path destination = target.resolve(relative);
          new File(String.valueOf(target)).mkdirs();
          if (!destination.toFile().isDirectory()) {
            Files.copy(child, destination, StandardCopyOption.REPLACE_EXISTING);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println(
          "Неправильно указан адрес папки источника или папки назначения. Введите еще раз.");
      check = false;
    }
    if (check == true) {
      System.out.println("Копирование выполнено успешно");
    }
  }
}


