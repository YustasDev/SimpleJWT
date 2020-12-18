import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.imgscalr.Scalr;


public class ImageMultipleThread implements Runnable {

  private static final Logger LOGGER = LogManager.getLogger(ImageMultipleThread.class);
  private static final Marker HISTORY_TREADS = MarkerManager.getMarker("HISTORY_TREADS");
  private static BufferedImage scaledImage;

  private File file;
  private String dstFolder;
  private int needWidth = 200;  // установим max значение ширины изображения
  private int needHeight = 200; // установим max значение высоты изображения


  public ImageMultipleThread(File file, String dstFolder) {
    this.file = file;
    this.dstFolder = dstFolder;
  }

  @Override
  public void run() {

    final long threadStartTime = System.currentTimeMillis();

    try {
      writeImageFile(resizeImage(file)); // запишем преобразованный файл
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error("File is {} It didn't work out {} ", file.getName(), e);
    }

    // записываем в threads.log данные о потоке и времени обработки файла
    LOGGER.info(HISTORY_TREADS, "Thread {}, running task {}", Thread.currentThread().getName(),
        file.getName());
    LOGGER
        .info(HISTORY_TREADS, "  Completed in {} ms", System.currentTimeMillis() - threadStartTime);
  }

  private void writeImageFile(BufferedImage scaledImage)  // метод записи файла
  {
    File newFile = new File(dstFolder + "/" + file.getName());
    try {
      ImageIO
          .write(scaledImage, "jpg", newFile); // записываем преобразованный файл по указанному пути
    } catch (IOException e) {
      e.printStackTrace();
      LOGGER.error("File write error {} ", e);
    }
  }

  private static BufferedImage resizeImage(File file) throws Exception {

    if (isJPG(file)) {
      try {
        BufferedImage imageToScale = ImageIO.read(file);  // читаем исходный файл

        int needWidth = 200; // заданное max значение ширины изображения
        int needHeight = 200; // заданное max значение высоты изображения
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
        scaledImage = Scalr.resize(imageToScale, Scalr.Method.ULTRA_QUALITY,
            Scalr.Mode.AUTOMATIC, needWidth, needHeight);

      } catch (IOException e) {
        e.printStackTrace();
        LOGGER.error("Resizing failed {} ", e);
      }
      return scaledImage;  // возвращаем преобразованный файл
    } else {
      throw new FileIsNotImageException("It's not an Image", file.getName());
    }
  }


  // TODO: доработать корректное определение файла изображения
  //  For JPEG image files begin with FF D8 and end with FF D9
  private static Boolean isJPG(File filename) throws Exception {
    DataInputStream ins = new DataInputStream(
        new BufferedInputStream(new FileInputStream(filename)));
    try {
      if (ins.readInt() == 0xffd8ffe0) {
        return true;
      } else {
        return false;
      }
    } finally {
      ins.close();
    }
  }
}
