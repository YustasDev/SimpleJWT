
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

  private static Scanner scanner;


  public static void main(String[] args) {

    for (; ; ) {
      scanner = new Scanner(System.in);

      String path = receivePath("Введите путь к папке. Образец: D:/Games");
      File folder = new File(path);

      try {
        double partitionSize = getListFiles(folder);

        printSizeFile(partitionSize, path);

      } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Путь к папке (директории) не указан или указан неправильно");
      }
    }
  }

  private static void printSizeFile(double partitionSize, String path) {

    if (partitionSize < 1024) {
      System.out
          .println("Размер указанной директории  " + path + " = " + partitionSize + " Байт");
    } else if (partitionSize < 1048576) {
      System.out.printf("Размер указанной директории \"%s\" равен %.2f Кб \n", path,
          partitionSize / 1024);
    } else if (partitionSize < 1073741824) {
      System.out.printf("Размер указанной директории \"%s\" равен %.2f Мб \n", path,
          partitionSize / 1024 / 1024);
    } else {
      System.out.printf("Размер указанной директории \"%s\" равен %.2f Гб \n", path,
          partitionSize / 1024 / 1024 / 1024);
    }
  }

  public static double getListFiles(File dir) {
    double size = 0;
    File[] files = dir.listFiles();
    for (File file : files) {
      if (file.isFile()) {
        size += file.length();
      } else {
        size += getListFiles(file);
      }
    }
    return size;
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
}
