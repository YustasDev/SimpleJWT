import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws Exception {
    final String filePrefix = "res/region";

    // it's necessary to delete the files created during the last launch
    Files.list(Path.of("."))
        .filter(Files::isRegularFile)
        .filter(p -> p.getFileName().toString().startsWith(filePrefix))
        .forEach(path -> {
          try {
            Files.deleteIfExists(path);
          } catch (IOException e) {
            e.printStackTrace();
            System.out
                .println("Log: error while delete file:" + path.getFileName().toAbsolutePath());
          }
        });

    long generalStart = System.currentTimeMillis();
    final int maxThreads = 4;

    ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
    int firstNumberRegion = 1;
    int lastRegionNumber = 100;

    Collection<Runnable> threads = quadroLaunch(firstNumberRegion, lastRegionNumber, filePrefix,
        maxThreads);
    threads.forEach(executorService::submit);
    executorService.shutdown();

    try {
      executorService.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.exit(0);
    }
    System.out
        .println("SUCCESS: The final time " + (System.currentTimeMillis() - generalStart) + " ms");
  }

  private static Collection<Runnable> quadroLaunch(int firstNumberRegion, int lastRegionNumber,
      String prefix, int maxThreads) throws IOException {

    Collection<Runnable> threads = new ArrayList<>();

    for (int i = firstNumberRegion; i <= lastRegionNumber; i++) {
      long start = System.currentTimeMillis();
      String regionCode = i < 10 ? Loader.padNumber(i, 2) : String.valueOf(i);
      threads.add(new Loader(regionCode, start, prefix + i % maxThreads + ".txt"));
    }
    return threads;
  }
}
