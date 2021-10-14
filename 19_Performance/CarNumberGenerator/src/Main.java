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

    for (int regionCode = 1; regionCode < 100; regionCode++) {
//      Integer regCode = regionCode;
//      String fileName = "res/number";
//      fileName = fileName.concat(regCode.toString()).concat(".txt");
      String fileName = "res/numbers.txt";
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
      executorService.submit(new Loader(writer, regionCode, start));
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
