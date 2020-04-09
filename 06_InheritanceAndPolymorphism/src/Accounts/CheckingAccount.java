package Accounts;

import java.util.Scanner;

  public class CheckingAccount {
  final String COMMAND_PUT = "(?i)(PUT\\s+)(\\d+)";
  final String COMMAND_GET = "(?i)(GET\\s+)(\\d+)";
  final String COMMAND_CHECK = "(?i)CHECK";

  private Double account = 0.0;

  public void run() {

    printUsage();

    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {

      input = scanner.nextLine();

      if (input.matches(COMMAND_CHECK)) {
        System.out.println("На Вашем расчетном счете:  " + account + "рублей");
        continue;

      } else if (input.matches(COMMAND_PUT)) {
        putInCheckingAccount(input);
      }

      else if (input.matches(COMMAND_GET)) {
        getFromCheckingAccount(input);
      }

      else {
        System.out.println("Команда не распознана. Введите еще раз.");
      }

    }
  }

  private void getFromCheckingAccount(String input) {
    Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
    account = account - indexOfCommand;
    if (account >= 0){
      System.out.println("Вы сняли:  " + indexOfCommand + "рублей");
      System.out.println("На Вашем расчетном счете:  " + account + "рублей");
    } else{
      account += indexOfCommand;
      System.out.println("Указанная Вами сумма " + indexOfCommand + "рублей, больше размера депозита");
      System.out.println("Операция не может быть выполнена. На Вашем расчетном счете:  " + account + "рублей");
    }
  }

  private void putInCheckingAccount(String input) {
    Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
    account += indexOfCommand;
    System.out.println("Вы внесли:  " + indexOfCommand + "рублей");
    System.out.println("На Вашем расчетном счете:  " + account + "рублей");

  }

  public static void printUsage() {
    System.out.println("Правила использования Вашего расчетного счета: ");
    System.out.println("Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
    System.out.println("Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму");
    System.out.println("Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
    System.out.println("Вызовите команду : ");
  }

}
