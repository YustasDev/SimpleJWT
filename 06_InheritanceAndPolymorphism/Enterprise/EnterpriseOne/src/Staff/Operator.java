package Staff;

public class Operator extends Specialist {

  public double operatorSalary = 30000;
  static int countOfOperators;

  public Operator(String name, int id, double salary) {
    this.name = "Operator";
    this.id = id;
    this.salary = operatorSalary;
    countOfOperators += 1;
  }

  @Override
  public double getMonthSalary() {
    return operatorSalary;


  }
 }


