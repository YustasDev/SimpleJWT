package Staff;

public class TopManager extends Specialist {

  final double TOP_MANAGER_BONUS = 300000;
  double baseTopManagerSalary = 200000;
  double topManagerSalary;
  static int countOfTopManagers;







   public TopManager(String name, int id, double salary) {
     this.name = "TopManager";
     this.id = id;
     this.salary = topManagerSalary;
    countOfTopManagers += 1;


   }

  @Override
  public double getMonthSalary() {
    return topManagerSalary;
  }



}
