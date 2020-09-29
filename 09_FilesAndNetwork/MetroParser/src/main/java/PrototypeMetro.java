
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

  public PrototypeMetro() {}

  public PrototypeMetro(Map <String, List<String>> stations, Set<List<Connections>> connections, List<MetroLine> lines) {
    this.stations = stations;
    this.connections = connections;
    this.lines = lines;
  }

  public Map<String, List<String>> getStations() {
    return stations;
  }

  public void setStations(Map<String, List<String>> stations) {
    this.stations = stations;
  }

  public Set<List<Connections>> getConnections() {
    return connections;
  }

  public void setConnections(Set <List<Connections>> connections) {
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

