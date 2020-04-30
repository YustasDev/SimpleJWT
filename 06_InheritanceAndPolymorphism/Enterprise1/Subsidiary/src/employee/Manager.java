package employee;

public class Manager extends AbstractEmployee {

  public static final Double MANAGER_BONUS_PERCENT = 0.05D;
  protected Double earnedMoney;

  public Manager(Long id, String name, Double baseMonthSalary, Double earnedMoney) {
    super(id, name, baseMonthSalary);
    this.earnedMoney = earnedMoney;
  }

  /**
   * зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных денег для компании
   */
  @Override
  public Double getMonthSalary() {
    return baseMonthSalary + earnedMoney * MANAGER_BONUS_PERCENT;
  }

  public Double getEarnedMoney() {
    return earnedMoney;
  }

  public void setEarnedMoney(Double earnedMoney) {
    this.earnedMoney = earnedMoney;
  }
}


