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


  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!Connections.class.isAssignableFrom(obj.getClass())) {
      return false;
    }

    final Connections other = (Connections) obj;
    if ((this.lineNo == null) ? (other.lineNo != null) : !this.lineNo.equals(other.lineNo)) {
      return false;
    }

    if ((this.transferStation == null) ? (other.transferStation != null) : !this.transferStation.equals(other.transferStation)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((lineNo == null) ? 0 : lineNo.hashCode());
    result = prime * result + ((transferStation == null) ? 0 : transferStation.hashCode());
    return result;
  }
}
