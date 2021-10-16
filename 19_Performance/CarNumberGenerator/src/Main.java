import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.PrintWriter;
import java.io.Writer;
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
    while (regCode < 100) {
      for (int count = 1; count < 5; count++) {
        String regionCode = Loader.padNumber(regCode, 2);
        String fileName = "res/number";
        fileName = fileName.concat(String.valueOf(count)).concat(".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        executorService.execute(new Loader(writer, regionCode, start));
        regCode++;
      }
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
