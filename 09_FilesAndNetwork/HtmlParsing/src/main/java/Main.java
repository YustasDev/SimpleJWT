import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

  private static final String IMAGE_DESTINATION_FOLDER = "C:/Images from URL";

  public static void main(String[] args) {

    String urlNeed = "https://lenta.ru/";
    Document docLenta = null;
    try {
      docLenta = Jsoup.connect(urlNeed).get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    //StringBuilder stringBuilder = new StringBuilder("");
    ArrayList<String> listImg = new ArrayList<>();


    int i = 0;
    for(Element docs : docLenta.select("img")){
      listImg.add(docs.attr("abs:src")+"\n");

//      ++i;
//      System.out.println(docs);
//      System.out.println(i);
    }

    //listImg.stream().forEach(System.out::println);

    for (String e : listImg) {
      downloadImage(e);
    }

//    String title = docLenta.title();
//
//    System.out.println(title);

  }

  private static void downloadImage(String strImageURL) {
    //get file name from image path
    String strImageName =
        strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );

    System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

    try {

      //open the stream from URL
      URL urlImage = new URL(strImageURL);
      InputStream in = urlImage.openStream();

      byte[] buffer = new byte[4096];
      int n = -1;

      OutputStream os =
          new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );

      //write bytes to the output stream
      while ( (n = in.read(buffer)) != -1 ){
        os.write(buffer, 0, n);
      }

      //close the stream
      os.close();

      System.out.println("Image saved");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}