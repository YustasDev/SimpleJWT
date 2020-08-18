


public class MetroStation {
    String number;
    String name;

    public MetroStation(String number, String name) {
      this.number = number;
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
