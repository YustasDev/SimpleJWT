package company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import employee.Employee;

public class Company {

  private static final Comparator<Employee> BY_SALARY_ASC = Comparator.comparingDouble(Employee::getMonthSalary);
  private static final Comparator<Employee> BY_SALARY_DESC = BY_SALARY_ASC.reversed();

  private final Map<Long, Employee> staff = new HashMap<>();
  private Double income;

  public Company(Double income) {
    this.income = income;
  }

  public void hire(Employee employee) {
    staff.put(employee.getId(), employee);
    employee.setCompany(this);
  }

  public void hireAll(Collection<? extends Employee> employees) {
    for(Employee employee: employees) {
      hire(employee);
    }
  }

  public void fire(Employee employee) {
    staff.remove(employee.getId());
    employee.setCompany(null);
  }



  public Double getIncome(){
    return income;
  }

  public List<Employee> getTopSalaryStaff(int count) {
    return getTop(count, BY_SALARY_DESC);
  }

  public List<Employee> getLowestSalaryStaff(int count) {
    return getTop(count, BY_SALARY_ASC);
  }

  public List<Employee> getStaff() {
    return new ArrayList<>(this.staff.values());
  }


  private List<Employee> getTop(int count, Comparator<Employee> comparator) {
    List<Employee> list = new ArrayList<>(staff.values());
    list.sort(comparator);
    return list.subList(0, Math.min(list.size(), count) - 1);
  }

}


