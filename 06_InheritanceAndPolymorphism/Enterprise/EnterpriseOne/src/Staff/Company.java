package Staff;


import java.util.Random;

public class Company {










  public static int getIncome(){

    Random rnd = new Random();
    int income = rnd.nextInt(20000000) + 1;

    return income;
  }



}
