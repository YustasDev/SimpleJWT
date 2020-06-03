package employee;

import company.Company;

public interface Employee {

  /**
   * @return - идентификационный номер сотрудника
   */
  Long getId();

  /**
   * @return - имя сотрудника
   */
  String getName();

  /**
   * метод расчта месечной заработной платы
   * @return - месячная заработная плата сотрудника
   */
  Double getMonthSalary();

  /**
   * устанавливает базовую иесячную зарплату сотрудника
   * @param baseMonthSalary - базовая месячная зарплата сотрудника
   */
  void setBaseMonthSalary(Double baseMonthSalary);

  void setCompany(Company company);
}
