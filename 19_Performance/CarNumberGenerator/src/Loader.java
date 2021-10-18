import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class Loader implements Runnable {

  static final DecimalFormat dF00 = new DecimalFormat("00");
  static final DecimalFormat dF000 = new DecimalFormat("000");

  private String regionCode;
  private long start;
  private String fileName;


  public Loader(String regionCode, long start, String fileName) throws IOException {
    this.regionCode = regionCode;
    this.start = start;
    this.fileName = fileName;
  }

  @Override
  public void run() {

    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(fileName, true));
    } catch (IOException e) {
      e.printStackTrace();
    }

    char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    StringBuilder builder = new StringBuilder();
    for (int number = 1; number < 1000; number++) {
      for (char firstLetter : letters) {
        for (char secondLetter : letters) {
          for (char thirdLetter : letters) {
            builder.append(firstLetter);
            if (number < 100) {
              builder.append(padNumber(number, 3));
            } else {
              builder.append(String.valueOf(number));
            }
            builder.append(secondLetter);
            builder.append(thirdLetter);
            builder.append(regionCode);
            builder.append("\n");
          }
        }
      }
    }
    try {
      writer.write(builder.toString());
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println((System.currentTimeMillis() - start) + " ms");
  }

  public static String padNumber(int number, int numberLength) {
    String numberStr = Integer.toString(number);
    int padSize = numberLength - numberStr.length();

    if (padSize == 1) {
      numberStr = dF00.format(number);
    }
    if (padSize == 2) {
      numberStr = dF000.format(number);
    }
    return numberStr;
  }
}
