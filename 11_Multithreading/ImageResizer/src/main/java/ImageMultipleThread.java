import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class ImageMultipleThread implements Runnable {

  private File[] files;
  private  String dstFolder;
  private int needWidth;
  private int needHeight;

  public ImageMultipleThread(File[] files, String dstFolder, int needWidth, int needHeight) {
    this.files = files;
    this.dstFolder = dstFolder;
    this.needWidth = needWidth;
    this.needHeight = needHeight;
  }

  @Override
  public void run() {

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
            Scalr.Mode.AUTOMATIC, needWidth, needHeight);

        File newFile = new File(dstFolder + "/" + file.getName());
        ImageIO.write(scaledImage, "jpg", newFile);

      }
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Resizing failed");
    }
  }
}
