package employee;


public class TopManager extends AbstractEmployee {
  private final static Double COMPANY_INCOME_BONUS_THRESHOLD = 10_000_000D;
  private final static Double BONUS_PERCENT = 1.5D;

  public TopManager(Long id, String name, Double baseMonthSalary) {
    super(id, name, baseMonthSalary);
  }

  /**
   * зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы, если доход компании более 10 млн рублей
   */
  @Override
  public Double getMonthSalary() {
    if (company.calculatedGetIncome() > COMPANY_INCOME_BONUS_THRESHOLD) {
      return baseMonthSalary + baseMonthSalary * BONUS_PERCENT;
    } else {
      return baseMonthSalary;
    }
  }
}


