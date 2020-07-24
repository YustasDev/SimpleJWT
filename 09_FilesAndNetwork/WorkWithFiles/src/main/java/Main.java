
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

  private static ArrayList<File> listWithFileNames = new ArrayList<>();
  private static Scanner scanner;

  public static void main(String[] args) {

    for (; ; ) {
      scanner = new Scanner(System.in);

      String path = receivePath("Введите путь к папке. Образец: D:/Games");

      try {
        getListFiles(path);

        Double partitionSize = listWithFileNames.stream().mapToDouble(x -> x.length()).sum();
        if (partitionSize < 1024) {
          System.out
              .println("Размер указанной директории  " + path + " = " + partitionSize + " Байт");
        } else if (partitionSize < 1048576) {
          System.out.println(
              "Размер указанной директории  " + path + " = " + partitionSize / 1024 + " Kб");
        } else if (partitionSize < 1073741824) {
          System.out.println(
              "Размер указанной директории  " + path + " = " + partitionSize / 1024 / 1024 + " Мб");
        } else {
          System.out.println(
              "Размер указанной директории  " + path + " = " + partitionSize / 1024 / 1024 / 1024
                  + " Гб");
        }
      } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Путь к папке (директории) не указан или указан неправильно");
      }
      listWithFileNames.clear();
    }
  }


  public static void getListFiles(String str) {
    File folder = new File(str);
    File[] files = folder.listFiles();
    for (File file : files) {
      if (file.isFile()) {
        listWithFileNames.add(file);
      } else if (file.isDirectory()) {
        getListFiles(file.getAbsolutePath());
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
    }
  }
}
