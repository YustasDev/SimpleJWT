import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();

    for (int regionCode = 1; regionCode < 100; regionCode++) {
      Integer regCode = regionCode;
      String fileName = "res/number";
      fileName = fileName.concat(regCode.toString()).concat(".txt");
      PrintWriter writer = new PrintWriter(fileName);
      Loader loader = new Loader(writer, regionCode, start);
      loader.start();
    }
  }
}
