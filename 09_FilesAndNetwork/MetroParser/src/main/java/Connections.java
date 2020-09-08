import java.util.List;
import java.util.Map;
import java.util.Set;

public class Connections {

  Set<List<Station>> connections;

  public Set<List<Station>> getConnections() {
    return connections;
  }

  public void setStations(Set<List<Station>> connections) {
    this.connections = connections;
  }


  @Override
  public String toString() {
    return "Connections{" +
        "connections = '" + connections + '\'' +
        '}';
  }
}
