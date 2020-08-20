
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetroStation {
    private String number;
    private String name;


//  @JsonProperty("Number Station")
//  private String number;
//
//  @JsonProperty("Name Station")
//  private String name;

  public MetroStation() {}


    public MetroStation(String number, String name) {
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
      return "MetroStation{" +
          "number='" + number + '\'' +
          ", name='" + name + '\'' +
          '}';
    }
  }
