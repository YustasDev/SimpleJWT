import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class Loader extends Thread {
  static final DecimalFormat dF00 = new DecimalFormat("00");
  static final DecimalFormat dF000 = new DecimalFormat("000");

  private PrintWriter writer;
  private Integer regionCode;

  public Loader(PrintWriter writer, Integer regionCode) {
    this.writer = writer;
  }

   @Override
    public void run() {

       char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                        }
                    }
                }
            }
          writer.write(builder.toString());
          writer.flush();
          writer.close();
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        if (padSize==1) {
            numberStr = dF00.format(number);
        }
        if (padSize==2) {
            numberStr = dF000.format(number);
        }

//        for (int i = 0; i < padSize; i++) {
//            numberStr = '0' + numberStr;
//        }
        return numberStr;
    }
}
