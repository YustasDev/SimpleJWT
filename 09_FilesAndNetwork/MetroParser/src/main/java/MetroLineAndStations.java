import java.util.List;

public class MetroLineAndStations {
  private String numberLine;
  private String nameLine;
  private List<MetroStation> stationList;


  public MetroLineAndStations() {
  }

  public MetroLineAndStations(String number, String name, List<MetroStation> stationList) {
    this.numberLine = number;
    this.nameLine = name;
    this.stationList = stationList;
  }

  public String getNumber() {
    return numberLine;
  }

  public void setNumber(String number) {
    this.numberLine = number;
  }

  public String getName() {
    return nameLine;
  }

  public void setName(String name) {
    this.nameLine = name;
  }

  public List<MetroStation> getStations() {
    return stationList;
  }

  public void setStation (List <MetroStation> station) {
    this.stationList = station;
  }

  @Override
  public String toString() {
    return "MetroLineAndStations {" +
        "numberLine='" + numberLine + '\'' +
        ", nameLine='" + nameLine + '\'' +
        ", stationList='" + stationList + '\'' +
        '}';
  }
}

