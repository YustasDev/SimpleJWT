package company;

import employee.Employee;
import employee.IncomeReceiveable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class Company {

  private static final Comparator<Employee> BY_SALARY_ASC = Comparator.comparingDouble(Employee::getMonthSalary);
  private static final Comparator<Employee> BY_SALARY_DESC = BY_SALARY_ASC.reversed();

  public final Map<Long, Employee> staff = new HashMap<>();

  public Company() {
  }

  public Double calculatedGetIncome(){
     Double income = 0.0D;
      for (Employee employee: staff.values()){
        if(employee instanceof IncomeReceiveable) {
          IncomeReceiveable incomeReceiveable = (IncomeReceiveable)employee;
          income += incomeReceiveable.getIncome();
        }
      }
      return income;
    }

  public Consumer<Entry<Long, Employee>> hire(Employee employee) {
    staff.put(employee.getId(), employee);
    employee.setCompany(this);
    return null;
  }

//  public void hireAll(Collection<? extends Employee> employees) {
//    for(Employee employee: employees) {
//      hire(employee);
//    }
//  }

  public void hireAll(Employee employee) {
    Consumer<Entry<Long, Employee>> action = hire(employee);
    staff.entrySet().forEach(action);



//    for(Employee employee: employees) {
//      hire(employee);
    }




    public void fire(Employee employee) {
    staff.remove(employee.getId());
    employee.setCompany(null);
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
    return list.subList(0, Math.min(list.size(), count));
  }
}


