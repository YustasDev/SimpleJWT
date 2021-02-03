import java.util.Objects;

public class PersonalAccount implements Account {

  private long money;
  private String accNumber;
  private boolean locked;

  public PersonalAccount() {
  }

  public PersonalAccount(long money, String accNumber, boolean locked) {
    this.money = money;
    this.accNumber = accNumber;
    this.locked = locked;
  }

  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }

  public String getAccNumber() {
    return accNumber;
  }

  public void setAccNumber(String accNumber) {
    this.accNumber = accNumber;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PersonalAccount)) {
      return false;
    }
    PersonalAccount that = (PersonalAccount) o;
    return getMoney() == that.getMoney() &&
        getAccNumber().equals(that.getAccNumber());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMoney(), getAccNumber());
  }

  @Override
  public String toString() {
    return "Для счета № " + accNumber + "  " +
        "Остаток на счете составляет - '" + money +
        "'";
  }
}
