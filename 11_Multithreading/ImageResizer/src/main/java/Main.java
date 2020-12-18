import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {

    String OS = System.getProperty("os.name")
        .toLowerCase(); // получаем информацию об используемой операционной системе
    String srcFolder = "c:/JAVA/sourceImages";
    String dstFolder = "c:/JAVA/destinationImages";
    int cores = Runtime.getRuntime()
        .availableProcessors(); // количество используемых ядер процессора(-ов)

    System.out.println("Your operating system is  " + OS);
    System.out.println("CPU  " + cores + "  cores");

    File srcDir = new File(srcFolder);
    File[] files = srcDir.listFiles();  // создадим массив файлов из указанной папки
    int arraySize = files.length;
    System.out.println("Общее количество файлов для обработки: " + arraySize);

    long GeneralStart = System
        .currentTimeMillis();  // определим начало работы по преобразованию изображений

    ExecutorService executorService = Executors
        .newFixedThreadPool(cores); // запустим на исполнение кол-во потоков = cores

    for (File file : files) {   // перебираем все файлы в массиве
      executorService.submit(new ImageMultipleThread(file,
          dstFolder));  // передаем на исполнение созданный объект с заданными параметрами
    }

    // останавливаем все потоки исполнения, находящиеся под управлением экземпляра ExecutorService
    executorService.shutdown();

    try {
      executorService.awaitTermination(1,
          TimeUnit.MINUTES); // см. https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("The final time " + (System.currentTimeMillis() - GeneralStart) + " ms");
  }
}