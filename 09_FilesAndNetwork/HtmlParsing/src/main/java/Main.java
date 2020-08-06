import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

  private static final String IMAGE_DESTINATION_FOLDER = "c:/images";
  private static final String URL_NEED = "https://lenta.ru/";

  public static void main(String[] args) {

    new File(String.valueOf(IMAGE_DESTINATION_FOLDER)).mkdirs();
    Document docLenta = null;
    try {
           docLenta = Jsoup
          .connect(URL_NEED)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ArrayList<String> listImg = new ArrayList<>();

    for(Element docs : docLenta.select("img"))
    {
      listImg.add(docs.attr("abs:src")+"\n");
    }

    for (String e : listImg) {
      downloadImage(e);
    }
  }

  private static void downloadImage(String strImageURL) {
    //get file name from image path
    String strImageName =
        strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );

    System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

    try {
      URL urlImage = new URL(strImageURL);
      InputStream input = urlImage.openStream();

      String s = IMAGE_DESTINATION_FOLDER + "/" + strImageName;
      System.out.println(s);

      Path path = Path.of(s);
      Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);


//
//      byte[] buffer = new byte[4096];
//      int n = -1;
//
//      OutputStream os =
//          new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );
//
//      //write bytes to the output stream
//      while ( (n = in.read(buffer)) != -1 ){
//        os.write(buffer, 0, n);
//      }
//
//      //close the stream
//      os.close();

      System.out.println("Image saved");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}