import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.PrintWriter;

public class Main {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();

    for (int regionCode = 1; regionCode < 100; regionCode++) {
      Integer regCode = regionCode;
      String fileName = "res/number";
      fileName = fileName.concat(regCode.toString()).concat(".txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      Loader loader = new Loader(writer, regionCode, start);
      loader.start();
    }
  }
}
