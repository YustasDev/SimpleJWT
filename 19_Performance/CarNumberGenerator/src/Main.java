import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws Exception {
    long GeneralStart = System.currentTimeMillis();
    long start = System.currentTimeMillis();

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    int regCode = 1;
    int counterThreads = 0;

    while (regCode < 100) {
           String regionCode = Loader.padNumber(regCode, 2);
           executorService.execute(new Loader(regionCode, start, counterThreads));
           regCode++;
           counterThreads++;
           if (counterThreads>3) {counterThreads = 0;} // в массиве имен файлов - 4 элемента
      }

      executorService.shutdown();

      try {
        executorService.awaitTermination(1, TimeUnit.MINUTES);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("The final time " + (System.currentTimeMillis() - GeneralStart) + " ms");
    }
  }
