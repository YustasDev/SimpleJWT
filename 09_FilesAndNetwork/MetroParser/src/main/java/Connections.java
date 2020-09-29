import java.util.List;
import java.util.Map;
import java.util.Set;

public class Connections {

  private String lineNo;
  private String transferStation;


  public Connections() {
  }

  public Connections(String lineNo, String transferStation) {
    this.lineNo = lineNo;
    this.transferStation = transferStation;
  }

  public String getLineNo() {
    return lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getTransferStation() {
    return transferStation;
  }

  public void setTransferStation(String transferStation) {
    this.transferStation = transferStation;
  }

  @Override
  public String toString() {
    return "Connections{" +
        "lineNo='" + lineNo + '\'' +
        ", transferStation='" + transferStation + '\'' +
        '}';
  }
}
