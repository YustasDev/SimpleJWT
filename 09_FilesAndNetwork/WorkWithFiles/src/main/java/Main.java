import static javax.print.attribute.standard.MediaSizeName.C;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Stream;


public class Main {

  private static ArrayList<File> listWithFileNames = new ArrayList<>();
  private static Scanner scanner;

  public static void main(String[] args) {

    for (; ; ) {
      scanner = new Scanner(System.in);

        String path = receivePath("Введите путь к папке. Образец: D:/Games");

     try {
      getListFiles(path);

        for (File file : listWithFileNames) {
          System.out.println(file.getName());
        }

       Double partitionSize = listWithFileNames.stream().mapToDouble(x -> x.length()).sum();
       System.out.println("Размер указанной директории =  " + partitionSize/1024/1024 + " Мб");

      }
     catch (Exception ex) {
       ex.printStackTrace();
       System.out.println("Путь к папке (директории) не указан или указан неправильно");
     }
      }
    }


  public static void getListFiles(String str) {
    File folder = new File(str);
    for (File s : folder.listFiles()) {
      if (s.isFile()) {
        listWithFileNames.add(s);
      } else if (s.isDirectory()) {
        getListFiles(s.getAbsolutePath());
      }
      else {System.out.println("Указан неправильный путь к папке");}
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
