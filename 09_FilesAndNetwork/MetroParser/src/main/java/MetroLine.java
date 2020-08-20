import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class MetroLine {
  private String number;
  private String name;
  private List<MetroStation> stations;


//  @JsonProperty("Number Line")
//  private String number;
//
//  @JsonProperty("Name Line")
//  private String name;
//
//  @JsonProperty("Stations")
//  private List<MetroStation> stations;


  public MetroLine(){}

  public MetroLine(String number, String name, List<MetroStation> stations) {
    this.number = number;
    this.name = name;
    this.stations = stations;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<MetroStation> getStations() {
    return stations;
  }

  public void setStations(List<MetroStation> stations) {
    this.stations = stations;
  }


  @Override
  public String toString() {
    return "MetroLine{" +
        "number='" + number + '\'' +
        ", name='" + name + '\'' +
        ", stations='" + stations + '\'' +
        '}';
  }
}

