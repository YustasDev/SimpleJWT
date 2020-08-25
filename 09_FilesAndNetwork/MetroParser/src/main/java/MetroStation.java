
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetroStation {

  @JsonProperty("Name Station")
  private String name;

  public MetroStation() {
  }
  public MetroStation(String name) {
    this.name = name;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Station{" +
       ", name='" + name + '\'' +
        '}';
  }
}
