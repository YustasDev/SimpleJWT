public class PersonalAccount implements Account{

    private long money;
    private String accNumber;

    public PersonalAccount() {
    }

    public PersonalAccount(long money, String accNumber) {
      this.money = money;
      this.accNumber = accNumber;
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

    @Override
    public String toString() {
      return "Для счета № " + accNumber + "  " +
          "Остаток на счете составляет - '" + money +
          "'";
    }
  }
