public class BankStatement {

  private Double income;
  private Double expense;
  private String organization;

  public BankStatement(String organization, Double income, Double expense) {
    this.organization = organization;
    this.income = income;
    this.expense = expense;
  }

  public Double getIncome() {
    return income;
  }

  public void setIncome(Double income) {
    this.income = income;
  }

  public Double getExpense() {
        return expense;
  }

  public void setExpense(Double expense) {
    this.expense = expense;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String toString() {
    return "От организации: " + organization + "  Доход = " + income + "  Расход =  " + expense;

  }
}

