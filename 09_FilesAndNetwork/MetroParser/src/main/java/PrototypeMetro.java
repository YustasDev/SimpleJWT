
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.TreeMap;

public class PrototypeMetro {

  @JsonProperty("stations")
  public Map<String, List<MetroStation>> stations = new TreeMap<>();

  @JsonProperty("lines")
  public List<MetroLine> lines = new ArrayList<>();

  public PrototypeMetro() {
  }

  public PrototypeMetro(Map <String, List<MetroStation>> stations, List<MetroLine> lines) {
    this.stations = stations;
    this.lines = lines;
  }

  public Map<String, List<MetroStation>> getStations() {
    return stations;
  }

  public void setStations(Map<String, List<MetroStation>> stations) {
    this.stations = stations;
  }

  public List<MetroLine> getLines() {
    return lines;
  }

  public void setLines(List<MetroLine> lines) {
    this.lines = lines;
  }


  @Override
  public String toString() {
    return "MetroLine{" +
        "line = '" + lines + '\'' +
        ", stations = '" + stations + '\'' +
        '}';
  }
}

