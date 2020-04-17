package Accounts;

import java.util.Scanner;

public class CheckingAccount extends Account {


  public CheckingAccount(double balance) {
    super(balance);
  }

  public void printUsage() {
    System.out.println("Правила использования Вашего расчетного счета: ");
    System.out.println(
        "Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
    System.out.println(
        "Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму");
    System.out.println(
        "Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
    System.out.println("Если Вы хотите прекратить работу со счетом, наберите команду: END");
    System.out.println("Вызовите команду : ");
  }
}
// new branch