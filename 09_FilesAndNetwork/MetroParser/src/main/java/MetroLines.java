import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MetroLines {

  @JsonProperty("Number Line")
  private String number;

  @JsonProperty("Name Line")
  private String name;


  public MetroLines() {
  }

  public MetroLines(String number, String name) {
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
    return "MetroLines{" +
        "number='" + number + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}


