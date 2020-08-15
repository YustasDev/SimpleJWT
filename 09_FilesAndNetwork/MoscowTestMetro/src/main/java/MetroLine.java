
public class MetroLine {
  final String number;
  final String name;

  public MetroLine(String number, String name) {
    this.number = number;
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "MetroLine{" +
        "number='" + number + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}


