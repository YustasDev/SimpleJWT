import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

  private static Scanner scanner;
  ArrayList<String> selectFiles = new ArrayList<>();

  public static void main(String[] args) throws IOException {

    for (; ; ) {
      scanner = new Scanner(System.in);

      String sourse = receivePath(
          "Введите путь к папке, которую надо переписать. Образец: С:/Games/Star Wars");
      String dest = receivePath(
          "Введите путь, куда надо переписать. Образец: D:/Archive/Games");

      Path sourcePath = Paths.get(sourse);
      Path destinationPath = Paths.get(dest);
      copyFolder (sourcePath, destinationPath);

//      try {
//        Files.copy(sourcePath, destinationPath,
//            StandardCopyOption.REPLACE_EXISTING);
//      } catch (FileAlreadyExistsException e) {
//        //destination file already exists
//      } catch (IOException e) {
//        //something else went wrong
//        e.printStackTrace();
//      }
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


  private static void copyFolder(Path source, Path target) throws IOException {
    try (Stream<Path> stream = Files.walk(source)) {
      stream.forEach((child) -> {
        try {
          Path relative = source.relativize(child);
          Path destination = target.resolve(relative);
          if (!destination.toFile().isDirectory()) {
            Files.copy(child, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
          throw new IllegalStateException(ex);
        }
      });
    }
  }
}



//
//
//
//
//    File folder = new File("путь");
//    File[] listOfFiles = folder.listFiles();
//
//    for (File f : listOfFiles) {
//      selectFiles.add(f + "");
//    }
//
//    File source = new File(" " + selectFiles);// ПОЛУЧАЮ СПИСОК ФАЙЛОВ ПРИСВАИВАЮ ПЕРЕМЕННУЮ С ФАЙЛАМИ
//    File dest = new File("путь");// ПРОПИСЫВАЮ ПУТЬ КУДА КОПИРОВАТЬ
//    copy(source, dest);
//  }
//
//  private static void copyDir(String sourceDirName, String targetSourceDir) {
//    File folder = new File(sourceDirName);
//
//    File[] listOfFiles = folder.listFiles();
//
//    Path destDir = Paths.get(targetSourceDir);
//    if (listOfFiles != null)
//      for (File file : listOfFiles)
//        Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
//
//  }
//
//
//}
