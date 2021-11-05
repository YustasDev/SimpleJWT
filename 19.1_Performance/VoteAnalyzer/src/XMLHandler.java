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
  long countIndexForSearchErrors = 0;


  public XMLHandler() {
    voterCounts = new HashMap<>();
  }


  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    try {
      if (qName.equals("voter") && voter == null) {
        Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
        voter = new Voter(attributes.getValue("name"), birthDay);
        ++countIndexForSearchErrors;
      }
      else if (qName.equals("visit") && voter != null) {
        int count = voterCounts.getOrDefault(voter, 0);
        voterCounts.put(voter, ++count);
      }
      else if (qName.equals("voters")){
      }
      else {
        System.err.println(qName.toString() + " <- current qName" + "  errorIndex  -> " + countIndexForSearchErrors);
        throw new InvalidPropertiesFormatException("Record in XML document not conform the requirements for parse of properties, as per the Properties specification.");
      }
    } catch (Exception pe) {
      pe.printStackTrace();
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("voter")) {
      voter = null;
    }
  }

  public void printDuplicatedVoters() {
    for(Voter voter : voterCounts.keySet()){
      int count = voterCounts.get(voter);
      if (count > 1){
        System.out.println(voter.toString() + " - " + count);
      }

    }


  }




}
