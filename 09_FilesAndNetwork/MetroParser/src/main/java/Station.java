

public class Station {

  String numberLine;
  String nameStation;

  public Station() {
  }

  public Station(String numberLine, String nameStation) {
    this.numberLine = numberLine;
    this.nameStation = nameStation;
  }

  public String getNumber() {
    return numberLine;
  }

  public void setNumber(String numberLine) {
    this.numberLine = numberLine;
  }

  public String getNameStation() {
    return nameStation;
  }

  public void setNameStation(String nameStation) {
    this.nameStation = nameStation;
  }

  @Override
  public String toString() {
    return "Station{" +
        "numberLine= '" + numberLine + '\'' +
        ", nameStation= '" + nameStation + '\'' +
        '}';
  }
}
