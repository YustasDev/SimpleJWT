package Staff;

  public class Manager extends Specialist {

    // бонус менеджера составляет 5% от заработанных денег для компании,
    // а для компании он зарабатывает сумму эквивалентную 20% своего заработка
    final double MANAGER_BONUS = 80000 * 0.2 * 0.05;
    double managerSalary = 80000;

    static int countOfManagers;

    public Manager(String name, int id, double salary) {
      this.name = "Manager";
      this.id = id;
      this.salary = managerSalary + MANAGER_BONUS;
      countOfManagers += 1;

    }

    @Override
    public double getMonthSalary() {
      return managerSalary;
    }


  }
