package Staff;

abstract class Specialist implements Employee {

  String name;   // имя сотрудника
  int id;        // идентификационный номер сотрудника
  double salary;  // зарплата сотрудника
  double income; // доход компании
  static int count;     // количество сотрудников

  public Specialist(String name, int id, double salary) {
    this.name = name;
    this.id = id;
    this.salary = salary;
    count +=1;
  }

  protected Specialist() {
  }

  @Override
  public double getMonthSalary() {
    return salary;
  }
}
