import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import org.imgscalr.Scalr;

public class Main {


  public static void main(String[] args) {

    String OS = System.getProperty("os.name").toLowerCase();
    String srcFolder = "c:/JAVA/sourceImages";
    String dstFolder = "c:/JAVA/destinationImages";
    int cores = Runtime.getRuntime().availableProcessors();
    int needWidth = 200;
    int needHeight = 200;

    System.out.println("Your operating system is  " + OS);
    System.out.println("CPU  " + cores + "  cores");

    File srcDir = new File(srcFolder);
    long start = System.currentTimeMillis();

    File[] files = srcDir.listFiles();
    System.out.println("Общее количество файлов для обработки: "+ files.length);

    try {
      for (File file : files) {
        BufferedImage imageToScale = ImageIO.read(file);
        if (imageToScale == null) {
          continue;
        }

        int originalWidth = imageToScale.getWidth(null);
        int originalHeight = imageToScale.getHeight(null);

        if (originalWidth > needWidth) {
          originalWidth /= 5;
          if (originalWidth < needWidth) needWidth = originalWidth;
        }

        if (originalHeight > needHeight) {
          originalHeight /= 5;
          if (originalHeight < needHeight)
            needHeight = originalHeight;
        }

        BufferedImage scaledImage = Scalr.resize(imageToScale, Scalr.Method.ULTRA_QUALITY,
            Scalr.Mode.FIT_EXACT, needWidth, needHeight);

          File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scaledImage, "jpg", newFile);

      }
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Resizing failed");
    }
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }
}