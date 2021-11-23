import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader {

  private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
  private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

  private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
  private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

  public static void main(String[] args) throws Exception {
    long before = memoryUsed();
    String fileName = "res/data-1572M.xml";
    long startTime = System.currentTimeMillis();
    SAXParserFactory saxFactory = SAXParserFactory.newInstance();
    SAXParser saxParser = saxFactory.newSAXParser();
    XMLHandler xmlHandler = new XMLHandler();
    saxParser.parse(new File(fileName), xmlHandler);
    System.err.println("Parsing duration: " + (System.currentTimeMillis() - startTime) + " ms");
    long after = memoryUsed();
    System.out.println("Diff: " + (after - before));

    DBConnection.printVoterCounts();
  }

  public static long memoryUsed() {
    Runtime runtime = Runtime.getRuntime();
    return runtime.totalMemory() - runtime.freeMemory();
  }
}