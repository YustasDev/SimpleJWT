package Accounts;

import java.util.Scanner;

  public class CheckingAccount extends Account {
  final String COMMAND_PUT = "(?i)(PUT\\s+)(\\d+)";
  final String COMMAND_GET = "(?i)(GET\\s+)(\\d+)";
  final String COMMAND_CHECK = "(?i)CHECK";
    final String COMMAND_END = "(?i)END";

      public CheckingAccount(double balance) {
      super(balance);
    }


      public void run() {

    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {

      input = scanner.nextLine();

      if (input.matches(COMMAND_CHECK)) {
        System.out.println("На Вашем расчетном счете:  " + getBalance() + "рублей");

      } else if (input.matches(COMMAND_PUT)) {
        putInCheckingAccount(input);
      }

      else if (input.matches(COMMAND_GET)) {
        getFromCheckingAccount(input);
      }

      else if (input.matches(COMMAND_END)) {
        System.out.println("Работа программы окончена");
        return;
      }

      else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }

    }
  }

    public double getBalance() {
      return balance;
    }


  public void getFromCheckingAccount(String input) {
    Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
    balance = balance - indexOfCommand;
    if (balance >= 0){
      System.out.println("Вы сняли:  " + indexOfCommand + "рублей");
      System.out.println("На Вашем расчетном счете:  " + balance + "рублей");
    } else{
      balance += indexOfCommand;
      System.out.println("Указанная Вами сумма " + indexOfCommand + "рублей, больше размера депозита");
      System.out.println("Операция не может быть выполнена. На Вашем расчетном счете:  " + balance + "рублей");
    }
  }

  public void putInCheckingAccount(String input) {
    Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
    balance += indexOfCommand;
    System.out.println("Вы внесли:  " + indexOfCommand + "рублей");
    System.out.println("На Вашем расчетном счете:  " + balance + "рублей");
  }

  public void printUsage() {
    System.out.println("Правила использования Вашего расчетного счета: ");
    System.out.println("Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
    System.out.println("Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму");
    System.out.println("Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
    System.out.println("Если Вы хотите прекратить работу со счетом, наберите команду: END");
    System.out.println("Вызовите команду : ");
  }

}
