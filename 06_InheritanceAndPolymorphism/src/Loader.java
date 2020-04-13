import Accounts.BankCardAccount;
import Accounts.CheckingAccount;
import Accounts.DepositaryAccount;
import java.io.IOException;
import java.util.Scanner;


public class Loader {

  static final String COMMAND_PUT = "(?i)(PUT\\s+)(\\d+)";
  static final String COMMAND_GET = "(?i)(GET\\s+)(\\d+)";
  static final String COMMAND_CHECK = "(?i)CHECK";
  static final String COMMAND_END = "(?i)END";

  public static void main(String[] args) throws IOException {

    printSelectAccount();

    Scanner scanner = new Scanner(System.in);
    String access;

    access = scanner.nextLine();
    if (access.equals("0001")) {
      CheckingAccount checkingAccount = new CheckingAccount(0);
      checkingAccount.printUsage();
      scanner = new Scanner(System.in);
      String input;

      while (true) {

        input = scanner.nextLine();

        if (input.matches(COMMAND_CHECK)) {
          checkingAccount.getBalance();
        } else if (input.matches(COMMAND_PUT)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
          checkingAccount.putInCheckingAccount(indexOfCommand);
        } else if (input.matches(COMMAND_GET)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
          checkingAccount.getFromCheckingAccount(indexOfCommand);
        } else if (input.matches(COMMAND_END)) {
          System.out.println("Работа программы окончена");
          return;
        } else {
          System.out.println("Команда не распознана. Введите еще раз.");
        }
      }
    } else if (access.equals("0002")) {
      DepositaryAccount depositaryAccount = new DepositaryAccount(0);
      depositaryAccount.printUsage();
      scanner = new Scanner(System.in);
      String input;

      while (true) {

        input = scanner.nextLine();

        if (input.matches(COMMAND_CHECK)) {
          depositaryAccount.getBalance();
        } else if (input.matches(COMMAND_PUT)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
          depositaryAccount.putInCheckingAccount(indexOfCommand);
        } else if (input.matches(COMMAND_GET)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
          if (depositaryAccount.ensureMonthFromLastDepositElapsed()) {
            depositaryAccount.getFromCheckingAccount(indexOfCommand);
          } else {
            System.out.println(
                "Операция не может быть выполнена. Внесение денежных средств было осуществлено менее месяца назад");
          }
        } else if (input.matches(COMMAND_END)) {
          System.out.println("Работа программы окончена");
          return;
        } else {
          System.out.println("Команда не распознана. Введите еще раз.");
        }
      }

    } else if (access.equals("0003")) {
      BankCardAccount bankCardAccount = new BankCardAccount(0);
      bankCardAccount.printUsage();
      scanner = new Scanner(System.in);
      String input;

      while (true) {

        input = scanner.nextLine();

        if (input.matches(COMMAND_CHECK)) {
          bankCardAccount.getBalance();
        } else if (input.matches(COMMAND_PUT)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
          bankCardAccount.putInCheckingAccount(indexOfCommand);
        } else if (input.matches(COMMAND_GET)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
          bankCardAccount.getFromCheckingAccount(indexOfCommand);
        } else if (input.matches(COMMAND_END)) {
          System.out.println("Работа программы окончена");
          return;
        } else {
          System.out.println("Команда не распознана. Введите еще раз.");
        }
      }
    } else {
      System.out.println("Код доступа не распознан, доступ запрещен");
    }
  }

  public static void printSelectAccount() {
    System.out.println("В Вашем распоряжении имеется:  ");
    System.out.println("1) Расчетный счет, на который можно положить или снять денежные средства");
    System.out.println(
        "2) Расчетный депозитарный счет, с которого нельзя снимать деньги в течение месяца после последнего внесения");
    System.out.println(
        "3) Карточный счет, при снятии денег с которого будет взиматься комиссия в размере 1%");
    System.out.println(
        "Код доступа к расчетному счету - 0001; Код доступа к расчетному депозитарному счету - 0002; Код доступа к карточному счету - 0003");
    System.out.println("Введите код доступа:  ");
  }
}
