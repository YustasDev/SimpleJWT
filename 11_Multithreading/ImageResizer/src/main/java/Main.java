import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import org.imgscalr.Scalr;

public class Main {


  public static void main(String[] args) {

    String OS = System.getProperty("os.name").toLowerCase(); // получаем информацию об используемой операционной системе
    String srcFolder = "c:/JAVA/sourceImages";
    String dstFolder = "c:/JAVA/destinationImages";
    int cores = Runtime.getRuntime().availableProcessors(); // количество используемых ядер процессора(-ов)
    int needWidth = 200;
    int needHeight = 200;

    System.out.println("Your operating system is  " + OS);
    System.out.println("CPU  " + cores + "  cores");

    File srcDir = new File(srcFolder);
    long start = System.currentTimeMillis();

    File[] files = srcDir.listFiles();
    System.out.println("Общее количество файлов для обработки: "+ files.length);

    ImageMultipleThread imageMultipleThread = new ImageMultipleThread(files, dstFolder, needWidth, needHeight);
    new Thread(imageMultipleThread).start();


//    int divider = files.length/cores;
//    for (int i = 0; i < divider; i++) {
//    }


    System.out.println("Duration: " + (System.currentTimeMillis() - start) + " ms");
  }
}