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
    System.out.println("Размер массива  " + carNumbers.size() + "  элементов" );

    long start1 = System.nanoTime();
    if (carNumbers.contains("А999АА161")) {
      long end1 = System.nanoTime();
      System.out.println("Поиск перебором: номер найден, поиск занял " + (end1 - start1) + "  ns");
    }
    else {
      long end11 = System.nanoTime();
      System.out.println("Поиск перебором: номер не найден, поиск занял  " + (end11 - start1) + "  ns");
    }

    Collections.sort(carNumbers);
    long start2 = System.nanoTime();
    int indexTrue = Collections.binarySearch(carNumbers, "А999АА161");
    if (indexTrue != -1) {
      long end2 = System.nanoTime();
      System.out.println("Бинарный поиск: номер найден, поиск занял  " + (end2 - start2) + "  ns");
    }
    else {
      long end22 = System.nanoTime();
      System.out.println("Бинарный поиск: номер не найден, поиск занял  " + (end22 - start2) + "  ns");
    }

    long start3 = System.nanoTime();
    if (carNumHashSet.contains("А999АА161")) {
      long end3 = System.nanoTime();
      System.out.println("Поиск в HashSet: номер найден, поиск занял " + (end3 - start3) + "  ns");
    }
    else {
      long end31 = System.nanoTime();
      System.out.println("Поиск в HashSet: номер не найден, поиск занял  " + (end31 - start3) + "  ns");
    }

    long start4ns = System.nanoTime();
    long start4 = System.currentTimeMillis();
    if (carNumTreeSet.contains("А999АА161")) {
      long end4ns = System.nanoTime();
      long end4 = System.currentTimeMillis();
      System.out.println("Поиск в TreeSet: номер найден, поиск занял " + (end4ns - start4ns) + "  ns");
      System.out.println("Поиск в TreeSet: номер найден, поиск занял " + (end4 - start4) + "  ms");
    }
    else {
      long end41ns = System.nanoTime();
      long end41 = System.currentTimeMillis();
      System.out.println("Поиск в TreeSet: номер не найден, поиск занял " + (end41ns - start4) + "  ns");
      System.out.println("Поиск в TreeSet: номер не найден, поиск занял  " + (end41 - start4) + "  ms");
    }
  }
}





