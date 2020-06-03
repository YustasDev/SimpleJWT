package employee;

import company.Company;

public abstract class AbstractEmployee implements Employee {
  /**
   * идентификационный номер сотрудника
   */
  protected final Long id;

  /**
   * имя сотрудника
   */
  protected String name;
  /**
   * базовая месячная зарплата сотрудника
   */
  protected Double baseMonthSalary;

  protected Company company;


  public AbstractEmployee(Long id, String name, Double baseMonthSalary) {
    this.id = id;
    this.name = name;
    this.baseMonthSalary = baseMonthSalary;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Базовая реализация - возвращает базовую месячную зарплату, подходит для рядовых сотрудников
   */
  @Override
  public Double getMonthSalary() {
    return baseMonthSalary;
  }

  @Override
  public void setBaseMonthSalary(Double baseMonthSalary) {
    this.baseMonthSalary = baseMonthSalary;
  }

  public Company getCompany() {
    return company;
  }

  @Override
  public void setCompany(Company company) {
    this.company = company;
  }

}


