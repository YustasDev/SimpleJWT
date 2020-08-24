
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrototypeMetro {

  @JsonProperty("Number Line")
  private String lineNumber;

  @JsonProperty("Stations")
  private List<MetroStation> stations;

  @JsonProperty("Lines")
  private List <MetroLines> lines;


  public PrototypeMetro() {
  }

  public PrototypeMetro(String number, List<MetroStation> stations, List<MetroLines> lines) {
    lineNumber = number;
    this.stations = stations;
    this.lines = lines;
  }

  public String getNumber() {
    return lineNumber;
  }

  public void setNumber(String number) {
    lineNumber = number;
  }

  public List<MetroStation> getStations() {
    return stations;
  }

  public void setStations(List<MetroStation> stations) {
    this.stations = stations;
  }

  public List<MetroLines> getLines() {
    return lines;
  }

  public void setLines(List<MetroLines> lines) {
    this.lines = lines;
  }


  @Override
  public String toString() {
    return "MetroLine{" +
        "number='" + lineNumber + '\'' +
        ", stations='" + stations + '\'' +
        ", lines='" + lines + '\'' +
        '}';
  }
}

