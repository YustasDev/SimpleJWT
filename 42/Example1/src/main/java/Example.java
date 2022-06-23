public class Example {

    public String st;
    public int num;
    public boolean bl;

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isBl() {
        return bl;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    @Override
    public String toString() {
        return "Example{" +
                "st='" + st + '\'' +
                ", num=" + num +
                ", bl=" + bl +
                '}';
    }

}
