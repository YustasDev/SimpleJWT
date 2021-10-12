import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class Loader extends Thread {
    static final DecimalFormat dF00 = new DecimalFormat("00");
    static final DecimalFormat dF000 = new DecimalFormat("000");

   @Override
    public void run() {

       // FileOutputStream writer = new FileOutputStream("res/numbers.txt");
       PrintWriter writer = null;
       try {
           writer = new PrintWriter("res/numbers.txt");
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }

       char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            StringBuffer buffer = new StringBuffer();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            buffer.append(firstLetter);
                            buffer.append(padNumber(number, 3));
                            buffer.append(secondLetter);
                            buffer.append(thirdLetter);
                            buffer.append(padNumber(regionCode, 2));
                            buffer.append("\n");
                        }
                    }
                }
            }
          writer.write(buffer.toString());
        }
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
