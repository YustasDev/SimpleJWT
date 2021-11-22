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
          //Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
          Date plug = new Date();
          String birthDay = attributes.getValue("birthDay");
          birthDay = birthDay.replace('.', '-');
          String name = attributes.getValue("name");
          voter = new Voter("plug", plug);
          //voter = new Voter(attributes.getValue("name"), birthDay);
          insertQuerry.append(
              (insertQuerry.length() == 0 ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");

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
    }


    public void printDuplicatedVoters() {
      System.out.println("Duplicated voters: ");
      for (Voter voter : voterCounts.keySet()) {
        int count = voterCounts.get(voter);
        if (count > 1) {
          System.out.println(voter.toString() + " - " + count);
        }
      }
      voterCounts = null;
    }

    public void printVotingStationWorkTimes() {
      System.out.println("Voting station work times: ");
      for (Integer votingStation : voteStationWorkTimes.keySet()) {
        WorkTime workTime = voteStationWorkTimes.get(votingStation);
        System.out.println("\t" + votingStation + " - " + workTime);
      }
      voteStationWorkTimes = null;

    }
  }
