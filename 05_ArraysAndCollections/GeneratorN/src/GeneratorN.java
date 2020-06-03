import static java.lang.System.nanoTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class GeneratorN {

  public static void main(String[] args) {

    String[] letters = {"А", "Б", "В", "Г", "Д", "Е", "К", "Л", "М", "Н", "О"};

    ArrayList<String> carNumbers = new ArrayList<>();
    HashSet<String> carNumHashSet = new HashSet<>();
    TreeSet<String> carNumTreeSet = new TreeSet<>();

    long start = System.currentTimeMillis();

    for (int l = 0; l < letters.length; l++) {
      for (int n = 1; n < 1000; n++) {
        for (int r = 1; r < 200; r++) {
          String currChar = letters[l];
          String number = String.format("%s%03d%s%s%03d", currChar, n, currChar, currChar, r);

          carNumbers.add(number);
          carNumHashSet.add(number);
          carNumTreeSet.add(number);
        }
      }
    }
    long end = System.currentTimeMillis();
    System.out.println("Длительность процесса формирования массива " + (end - start) + "  ms");
    System.out.println("Размер массива  " + carNumbers.size() + "  элементов");

    long start1 = System.nanoTime();
    boolean isFound = carNumbers.contains("А999АА161");
    long end1 = System.nanoTime();
    long time1 = end1 - start1;
    if (isFound) {
      System.out.println("Поиск перебором: номер найден, поиск занял " + time1 + "  ns");
    } else {
      System.out.println("Поиск перебором: номер не найден, поиск занял  " + time1 + "  ns");
    }

    Collections.sort(carNumbers);
    long start2 = System.nanoTime();
    int indexTrue = Collections.binarySearch(carNumbers, "Э999АА161");
    long end2 = System.nanoTime();
    long time2 = end2 - start2;
    if (indexTrue < 0) {
      System.out.println("Бинарный поиск: номер не найден, поиск занял  " + time2 + "  ns");
    } else {
      System.out.println("Бинарный поиск: номер найден, поиск занял  " + time2 + "  ns");
    }

    long start3 = System.nanoTime();
    boolean isFound3 = carNumHashSet.contains("А999АА161");
    long end3 = System.nanoTime();
    long time3 = end3 - start3;
    if (isFound3) {
      System.out.println("Поиск в HashSet: номер найден, поиск занял " + time3 + "  ns");
    } else {
      System.out.println("Поиск в HashSet: номер не найден, поиск занял  " + time3 + "  ns");
    }

    long start4 = System.nanoTime();
    boolean isFound4 = carNumTreeSet.contains("А999АА161");
    long end4 = System.nanoTime();
    Long time4 = end4 - start4;
    if (isFound4) {
      System.out.println("Поиск в TreeSet: номер найден, поиск занял " + time4 + "  ns");
    } else {
      System.out.println("Поиск в TreeSet: номер не найден, поиск занял " + time4 + "  ns");
    }

  }
}





