
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  private static int firstNumberRegion;
  private static int lastRegionNumber;
  private static String fileName;
  private static ExecutorService executorService;

  public static void main(String[] args) throws Exception {
    long GeneralStart = System.currentTimeMillis();

    String[] filesName = {"res/number1.txt", "res/number2.txt", "res/number3.txt",
        "res/number4.txt"};
    executorService = Executors.newFixedThreadPool(4);

    for (int i = 0; i < filesName.length; i++) {
      if (i == 0) {
        firstNumberRegion = 1;
        lastRegionNumber = 25;
      } else if (i == 1) {
        firstNumberRegion = 26;
        lastRegionNumber = 50;
      } else if (i == 2) {
        firstNumberRegion = 51;
        lastRegionNumber = 75;
      } else if (i == 3) {
        firstNumberRegion = 76;
        lastRegionNumber = 99;
      }
      fileName = filesName[i];
      quadroLaunch(firstNumberRegion, lastRegionNumber, fileName);
    }
    executorService.shutdown();

    try {
      executorService.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("The final time " + (System.currentTimeMillis() - GeneralStart) + " ms");
  }

  private static void quadroLaunch(int firstNumberRegion, int lastRegionNumber, String fileName)
      throws IOException {

    for (int i = firstNumberRegion; i <= lastRegionNumber; i++) {
      String regionCode;
      long start = System.currentTimeMillis();
      if (i < 10) {
        regionCode = Loader.padNumber(i, 2);
      } else {
        regionCode = (String.valueOf(i));
      }
      executorService.submit(new Loader(regionCode, start, fileName));
    }
  }
}
