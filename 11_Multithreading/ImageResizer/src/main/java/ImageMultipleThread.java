import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class ImageMultipleThread implements Runnable {

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
        BufferedImage imageToScale = ImageIO.read(file);

        int originalWidth = imageToScale.getWidth(null);
        int originalHeight = imageToScale.getHeight(null);

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

        BufferedImage scaledImage = Scalr.resize(imageToScale, Scalr.Method.ULTRA_QUALITY,
            Scalr.Mode.AUTOMATIC, needWidth, needHeight);

        File newFile = new File(dstFolder + "/" + file.getName());
        ImageIO.write(scaledImage, "jpg", newFile);


    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Resizing failed");
    }
    System.out.println("Completed in " + (System.currentTimeMillis() - start) + " ms");
  }
}
