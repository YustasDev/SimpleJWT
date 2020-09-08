
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Set;

public class PrototypeMetro {

  @JsonProperty("stations")
  public Map<String, List<String>> stations;

  @JsonProperty("connections")
  public Set<List<Connections>> connections;

  @JsonProperty("lines")
  public List<MetroLine> lines;

//  public PrototypeMetro(Map<String, List<String>> stations, List<MetroLine> lines,
//      Set<List<Transfers>> connections) {
//  }

  public PrototypeMetro(Map <String, List<String>> stations, List<MetroLine> lines, Set<List<Connections>> connections) {
    this.stations = stations;
    this.lines = lines;
    this.connections = connections;
  }


  public Map<String, List<String>> getStations() {
    return stations;
  }

  public void setStations(Map<String, List<String>> stations) {
    this.stations = stations;
  }

  public Set<List<Connections>> getTransfers() {
    return connections;
  }

  public void setTransfers(Set<List<Connections>> connections) {
    this.connections = connections;
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
        ", connections = '" + connections + '\'' +
        '}';
  }
}

