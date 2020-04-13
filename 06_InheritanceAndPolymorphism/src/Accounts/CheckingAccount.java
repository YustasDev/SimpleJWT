package Accounts;

import java.util.Scanner;

public class CheckingAccount extends Account {


  public CheckingAccount(double balance) {
    super(balance);
  }

  public double getBalance() {
    System.out.println("На Вашем расчетном счете:  " + balance + "рублей");
    return balance;
  }

  public void getFromCheckingAccount(Double indexOfCommand) {
    balance = balance - indexOfCommand;
    if (balance >= 0) {
      System.out.println("Вы сняли:  " + indexOfCommand + "рублей");
      System.out.println("На Вашем расчетном счете:  " + balance + "рублей");
    } else {
      balance += indexOfCommand;
      System.out
          .println("Указанная Вами сумма " + indexOfCommand + "рублей, больше размера депозита");
      System.out.println(
          "Операция не может быть выполнена. На Вашем расчетном счете:  " + balance + "рублей");
    }
  }

  public void putInCheckingAccount(Double indexOfCommand) {
    balance += indexOfCommand;
    System.out.println("Вы внесли:  " + indexOfCommand + "рублей");
    System.out.println("На Вашем расчетном счете:  " + balance + "рублей");
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
