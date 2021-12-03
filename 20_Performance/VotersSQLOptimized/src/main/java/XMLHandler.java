import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


  public class XMLHandler extends DefaultHandler {

    private Voter voter;
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private Map<Voter, Integer> voterCounts;
    private HashMap<Integer, WorkTime> voteStationWorkTimes;
    private static int countElementsForMultyInsert = 3000000;

    private static StringBuilder insertQuerry = new StringBuilder();

    public XMLHandler() {
      voterCounts = new HashMap<>();
      voteStationWorkTimes = new HashMap<>();
    }

    public static StringBuilder getInsertQuerry() {
      return insertQuerry;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {
      try {
        if (qName.equals("voter") && voter == null) {
          Date plug = new Date();
          String birthDay = attributes.getValue("birthDay");
          birthDay = birthDay.replace('.', '-');
          String name = attributes.getValue("name");
          voter = new Voter("plug", plug);  // it's just a plug, so as not to break the XMLHandler
          insertQuerry.append(
              (insertQuerry.length() == 0 ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");
          if (insertQuerry.length() > countElementsForMultyInsert) {
            DBConnection.executeMultyInsert();
            insertQuerry.setLength(0);
          }
        } else if (qName.equals("visit") && voter != null) {
          Date visitTime = visitDateFormat.parse(attributes.getValue("time"));
          Integer station = Integer.valueOf(attributes.getValue("station"));
          WorkTime workTime = getVoteStationWorkTimes().get(station);
          if (workTime == null) {
            workTime = new WorkTime();
          }
          workTime.addVisitTime(visitTime.getTime());
          voteStationWorkTimes.put(station, workTime);
          int count = voterCounts.getOrDefault(voter, 0);
          voterCounts.put(voter, ++count);
        } else if (qName.equals("voters")) {
        } else {
          System.err.println(qName.toString() + " <- current qName");
          throw new InvalidPropertiesFormatException(
              "Record in XML document not conform the requirements for parse of properties, as per the Properties specification.");
        }
      } catch (Exception pe) {
        pe.printStackTrace();
      }
    }

    private HashMap<Integer, WorkTime> getVoteStationWorkTimes() {
      return voteStationWorkTimes;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      if (qName.equals("voter")) {
        voter = null;
      }
      if (qName.equals("voters")) {
        if (insertQuerry.length() != 0) {
          try {
            DBConnection.executeMultyInsert();
          } catch (SQLException throwables) {
            throwables.printStackTrace();
          }
          insertQuerry.setLength(0);
        }
      }
    }
  }
