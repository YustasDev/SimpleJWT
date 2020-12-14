import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.imgscalr.Scalr;

public class ImageMultipleThread implements Runnable {

  private static final Logger LOGGER = LogManager.getLogger(Main.class);
  private static final Marker HISTORY_TREADS = MarkerManager.getMarker("HISTORY_TREADS");

  private File file;
  private String dstFolder;
  private int needWidth;
  private int needHeight;
  private long start;

  public ImageMultipleThread(File file, String dstFolder, int needWidth, int needHeight,
      long start) {
    this.file = file;
    this.dstFolder = dstFolder;
    this.needWidth = needWidth;
    this.needHeight = needHeight;
    this.start = start;
  }

  @Override
  public void run() {

    try {
      BufferedImage imageToScale = ImageIO.read(file);  // читаем файл

      int originalWidth = imageToScale.getWidth(null);  // получаем ширину
      int originalHeight = imageToScale.getHeight(null);  // ... и высоту исходного файла

     // если ширина и высота исходного файйла больше заданных, уменьшаем эти параметры в 5 раз
      if (originalWidth > needWidth) {
        originalWidth /= 5;
        if (originalWidth < needWidth) {
          needWidth = originalWidth;
        }
      }

      if (originalHeight > needHeight) {
        originalHeight /= 5;
        if (originalHeight < needHeight) {
          needHeight = originalHeight;
        }
      }

      // преобразуем файл используя потокобезопасную библиотеку imgscalr-lib
      BufferedImage scaledImage = Scalr.resize(imageToScale, Scalr.Method.ULTRA_QUALITY,
          Scalr.Mode.AUTOMATIC, needWidth, needHeight);

      File newFile = new File(dstFolder + "/" + file.getName());
      ImageIO.write(scaledImage, "jpg", newFile); // записываем преобразованный файл по указанному пути

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Resizing failed");
    }
    // записываем в threads.log данные о потоке и времени обработки файла
    LOGGER.info(HISTORY_TREADS, "Thread {}, running task {}", Thread.currentThread().getName(),
        file.getName());
    LOGGER.info(HISTORY_TREADS, "  Completed in {} ms", System.currentTimeMillis() - start);
  }
}
