import java.util.List;

public class MetroLine {
  final String number;
  final String name;
  final List<String> stations;


  public MetroLine(String number, String name, List<String> stations) {
    this.number = number;
    this.name = name;
    this.stations = stations;
  }

  public String getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }

  public List <String> getStations() {
    return stations;
  }


  @Override
  public String toString() {
    return "MetroLine{" +
        "number='" + number + '\'' +
        ", name='" + name + '\'' +
        ", stations= '" + stations + '\'' +
        '}';
  }
}


