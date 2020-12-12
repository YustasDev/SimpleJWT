import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class Main {

  private static final Logger LOGGER = LogManager.getLogger (Main.class);
  private static final Marker HISTORY_TREADS = MarkerManager.getMarker("HISTORY_TREADS");

  public static void main(String[] args) {

    String OS = System.getProperty("os.name")
        .toLowerCase(); // получаем информацию об используемой операционной системе
    String srcFolder = "c:/JAVA/sourceImages";
    String dstFolder = "c:/JAVA/destinationImages";
    int cores = Runtime.getRuntime()
        .availableProcessors(); // количество используемых ядер процессора(-ов)
    int needWidth = 200;
    int needHeight = 200;

    System.out.println("Your operating system is  " + OS);
    System.out.println("CPU  " + cores + "  cores");

    File srcDir = new File(srcFolder);
    long start = System.currentTimeMillis();

    File[] files = srcDir.listFiles();
    int arraySize = files.length;
    System.out.println("Общее количество файлов для обработки: " + arraySize);

    ExecutorService executorService = Executors.newFixedThreadPool(cores);

    for (int i = 0; i < arraySize; i++) {
      File file = files[i];
      final int fileNo = i;
      ImageMultipleThread imageMultipleThread = new ImageMultipleThread(file, dstFolder, needWidth,
          needHeight, start);
      executorService.submit(imageMultipleThread);
      executorService.submit((Runnable) () -> System.out
              .printf("thread %s running task #%d%n", Thread.currentThread().getName(), fileNo)
      );
    }
    executorService.shutdown();

    try {
      executorService.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("The final time " + (System.currentTimeMillis() - start) + " ms");
  }
}