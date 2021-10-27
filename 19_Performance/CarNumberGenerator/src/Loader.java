import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class Loader implements Runnable {

  static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
  private final String regionCode;
  private final long start;
  private final String filePrefix;


  public Loader(String regionCode, long start, String fileName) throws IOException {
    this.regionCode = regionCode;
    this.start = start;
    this.filePrefix = fileName;
  }

  @Override
  public void run() {
    StringBuilder builder = new StringBuilder();
    for (int number = 1; number < 1000; number++) {
      String numberFormatted = number < 100 ? padNumber(number, 3) : String.valueOf(number);
      for (char firstLetter : letters) {
        for (char secondLetter : letters) {
          for (char thirdLetter : letters) {
            builder.append(firstLetter)
                .append(numberFormatted)
                .append(secondLetter)
                .append(thirdLetter)
                .append(regionCode)
                .append("\n");
          }
        }
      }
    }
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filePrefix, true));
      writer.write(builder.toString());
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println((System.currentTimeMillis() - start) + " ms");
  }

  public static String padNumber(int number, int numberLength) {
    return String.format("%0" + numberLength + "d", number);
  }
}
