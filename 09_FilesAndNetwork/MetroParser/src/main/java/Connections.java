import java.util.List;
import java.util.Map;
import java.util.Set;

public class Connections {

  private String leftLineNo;
  private String leftStation;
  private String rightLineNo;
  private String rightStation;

  public Connections() {
  }

  public Connections(String leftLineNo, String leftStation, String rightLineNo, String rightStation) {
    this.leftLineNo = leftLineNo;
    this.leftStation = leftStation;
    this.rightLineNo = rightLineNo;
    this.rightStation = rightStation;
  }

  public String getLeftLineNo() {
    return leftLineNo;
  }

  public void setLeftLineNo(String leftLineNo) {
    this.leftLineNo = leftLineNo;
  }

  public String getLeftStation() {
    return leftStation;
  }

  public void setLeftStation(String leftStation) {
    this.leftStation = leftStation;
  }

  public String getRightLineNo() {
    return rightLineNo;
  }

  public void setRightLineNo(String rightLineNo) {
    this.rightLineNo = rightLineNo;
  }

  public String getRightStation() {
    return rightStation;
  }

  public void setRightStation(String rightStation) {
    this.rightStation = rightStation;
  }

  @Override
  public String toString() {
    return "Connections{" +
        "leftLineNo='" + leftLineNo + '\'' +
        ", leftStation='" + leftStation + '\'' +
        "rightLineNo='" + rightLineNo + '\'' +
        ", rightStation='" + rightStation + '\'' +
        '}';
  }
}
