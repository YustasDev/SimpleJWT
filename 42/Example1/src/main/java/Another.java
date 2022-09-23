import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Another implements MyInterface{

    Integer count;
    String fffmystring;

    public Another(Integer count, String fff) {
        this.count = count;
        this.fffmystring = fff;

    }

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public String getFffmystring() {return fffmystring;}
    public void setFffmystring(String fffmystring) {this.fffmystring = fffmystring;}


    public static void doSome (Logger log) {

        int z = 13;
        FileHandler fhLog2 = null;
        try {
            fhLog2 = new FileHandler("/home/progforce/2/myLogFile2.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.addHandler(fhLog2);
        SimpleFormatter formatter = new SimpleFormatter();
        fhLog2.setFormatter(formatter);

        // the following statement is used to log any messages
        log.info("It's static log from Another " + z);
    }


    @Override
    public String toString() {
        return "Another{" +
                "count=" + count +
                ", fffmystring='" + fffmystring + '\'' +
                '}';
    }

}
