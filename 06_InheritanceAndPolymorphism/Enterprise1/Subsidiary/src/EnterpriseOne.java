import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

import company.Company;
import employee.Employee;
import employee.Manager;
import employee.Operator;
import employee.TopManager;


  public class EnterpriseOne {
  private final static NumberFormat SALARY_FORMATTER = salaryFormatter();

  private static Random random = new Random();
  private static long nextEmployeeId = 1;

  private static NumberFormat salaryFormatter() {
    DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(Locale.getDefault());
    unusualSymbols.setGroupingSeparator(' ');
    unusualSymbols.setCurrencySymbol("руб.");
    return new DecimalFormat("#,##0 ¤", unusualSymbols);
  }

  private static Operator newOperator() {
    Long id = nextEmployeeId++;
    String name = "Вася";
    Double baseSalary = 20000 + 20000 * random.nextDouble();
    return new Operator(id, "Вася", baseSalary);
  }

  private static Manager newManager() {
    Long id = nextEmployeeId++;
    String name = "Коля";
    Double baseSalary = 50000 + 20000 * random.nextDouble();
    Double earnedMoney = 10_000_000 * random.nextDouble();
    return new Manager(id, name, baseSalary, earnedMoney);
  }

  private static TopManager newTopManager() {
    Long id = nextEmployeeId++;
    String name = "Виталий";
    Double baseSalary = 250000 + 250000 * random.nextDouble();
    return new TopManager(id, name, baseSalary);
  }


  private static <E> List<E> generate(int count, Supplier<E> generator) {
    List<E> result = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      result.add(generator.get());
    }
    return result;
  }

  public static void main(String[] args) {

    // Создайте и наймите в компанию:180 операторов Operator, 80 менеджеров по продажам Manager, 10 топ менеджеров TopManager
    Company company = new Company(25_000_000D);
    List<Operator> operators = generate(180, EnterpriseOne::newOperator);

    company.hireAll(operators);

    List<Manager> managers = generate(80, EnterpriseOne::newManager);
    company.hireAll(managers);

    List<TopManager> topManagers = generate(10, EnterpriseOne::newTopManager);
    company.hireAll(topManagers);

    // Распечатайте список состоящий из 10-15 самых высоких зарплат в компании
    List<Employee> top10 = company.getTopSalaryStaff(10);
    printSalaries("список состоящий из 10 самых высоких зарплат в компании", top10);

    // Распечатайте список из 30 самых низких зарплат в компании
    List<Employee> low30 = company.getLowestSalaryStaff(30);
    printSalaries("список из 30 самых низких зарплат в компании", low30);

    // Увольте 50% сотрудников
    List<Employee> staff = company.getStaff();
    for (int i = 0; i < staff.size(); i++) {
      if (i % 2 == 0) {
        company.fire(staff.get(i));
      }
    }

    // Распечатайте список состоящий из 10-15 самых высоких зарплат в компании
    top10 = company.getTopSalaryStaff(10);
    printSalaries("список состоящий из 10 самых высоких зарплат в компании", top10);

    // Распечатайте список из 30 самых низких зарплат в компании
    low30 = company.getLowestSalaryStaff(30);
    printSalaries("список из 30 самых низких зарплат в компании", low30);

  }

  static void printSalaries(String title, List<Employee> employees) {
    System.out.println(title);

    for (Employee employee : employees) {
      System.out.println(SALARY_FORMATTER.format(employee.getMonthSalary()));
    }
  }
}


