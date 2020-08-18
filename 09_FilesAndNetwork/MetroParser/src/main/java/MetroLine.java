import java.util.List;

public class MetroLine {
  final String number;
  final String name;
  final List<MetroStation> stations;

  public MetroLine(String number, String name, List<MetroStation> stations) {
    this.number = number;
    this.name = name;
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

