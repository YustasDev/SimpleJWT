import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class MetroLine {

  @JsonProperty("Number Line")
  private String number;

  @JsonProperty("Name Line")
  private String name;


  public MetroLine() {
  }

  public MetroLine(String number, String name) {
    this.number = number;
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "MetroLine{" +
        "number='" + number + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}

