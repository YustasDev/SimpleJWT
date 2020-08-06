import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

  private static final String IMAGE_DESTINATION_FOLDER = "c:/Users/Yustas/java_basics/09_FilesAndNetwork/HtmlParsing/images";
  private static final String URL_NEED = "https://lenta.ru/";
  private static int i = 1;

  public static void main(String[] args) {

    new File(String.valueOf(IMAGE_DESTINATION_FOLDER))
        .mkdirs();   // создаем директорию определенную заданием
    Document docLenta = null;
    try {           // используя jsoup создаем объект Document содержащий код страницы по указанному URL
      docLenta = Jsoup
          .connect(URL_NEED)
          .userAgent("Mozilla/5.0")
          .timeout(10 * 1000)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ArrayList<String> listImg = new ArrayList<>();

    for (Element docs : docLenta  // в коде страницы
        .select("img"))  // осуществляем поиск элементов соответствующих требованию
    {
      listImg.add(docs.attr("abs:src")); // прибавляем найденные элементы в список
    }

    for (String e : listImg) {       // для всех элементов списка вызываем метод загрузки картинок
      downloadImage(e);             // в который передаем абсолютный путь к ним, указанный на сайте
      ++i;
    }
  }

  private static void downloadImage(String strImageURL) {
    //получаем имя файла находящегося по указанному пути
    String preStrImageName =
        strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

    try {
      URL urlImage = new URL(strImageURL);
      InputStream input = urlImage.openStream();  // открываем поток из указанного URL

      System.out.println("Saving: " + preStrImageName + ", from: " + strImageURL);

      String regexNameFile = "[^a-zA-Z0-9._ -]"; // получаем допустимое имя для записываемого файла
      String strImageName = preStrImageName.replaceAll(regexNameFile, "");

      String destination = IMAGE_DESTINATION_FOLDER + "/" + strImageName;
      System.out.println("Into  " + destination);

      Path path = Path.of(destination);
      Files.copy(input, path,
          StandardCopyOption.REPLACE_EXISTING);  // записываем файл в заданную папку

      System.out
          .println("Image " + i + " saved");    // печатаем значение счетчика скачанных файлов

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
