

public class Another extends abstructTher implements MyInterface{

    Integer count;
    String fffmystring;


    public Another(Integer count, String fff) {
        this.count = count;
        this.fffmystring = fff;

    }

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public String getFffmystring() {return fffmystring;}
    public void setFffmystring(String fffmystring) {this.fffmystring = fffmystring;}

    @Override
    public String toString() {
        return "Another{" +
                "count=" + count +
                ", fffmystring='" + fffmystring + '\'' +
                '}';
    }

}
