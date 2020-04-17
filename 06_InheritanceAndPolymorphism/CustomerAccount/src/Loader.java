import ClientAccount.LegalPerson;
import ClientAccount.PrivatePerson;
import ClientAccount.SoleProprietor;
import java.io.IOException;
import java.math.BigDecimal;
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
      BigDecimal balance = new BigDecimal(0.0);
      PrivatePerson privatePerson = new PrivatePerson(balance);
      privatePerson.printUsage();
      scanner = new Scanner(System.in);
      String input;

      while (true) {

        input = scanner.nextLine();

        if (input.matches(COMMAND_CHECK)) {
          privatePerson.getBalance();
        } else if (input.matches(COMMAND_PUT)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
          privatePerson.putInAccount(indexOfCommand);
        } else if (input.matches(COMMAND_GET)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
          privatePerson.getFromAccount(indexOfCommand);
        } else if (input.matches(COMMAND_END)) {
          System.out.println("Работа программы окончена");
          return;
        } else {
          System.out.println("Команда не распознана. Введите еще раз.");
        }
      }
    } else if (access.equals("0002")) {
      BigDecimal balance = new BigDecimal(0.0);
      LegalPerson legalPerson = new LegalPerson(balance);
      legalPerson.printUsage();
      scanner = new Scanner(System.in);
      String input;

      while (true) {
        input = scanner.nextLine();

        if (input.matches(COMMAND_CHECK)) {
          legalPerson.getBalance();
        } else if (input.matches(COMMAND_PUT)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
          legalPerson.putInAccount(indexOfCommand);
        } else if (input.matches(COMMAND_GET)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
          legalPerson.getFromAccount(indexOfCommand);
        } else if (input.matches(COMMAND_END)) {
          System.out.println("Работа программы окончена");
          return;
        } else {
          System.out.println("Команда не распознана. Введите еще раз.");
        }
      }

    } else if (access.equals("0003")) {
      BigDecimal balance = new BigDecimal(0.0);
      SoleProprietor soleProprietor = new SoleProprietor(balance);
      soleProprietor.printUsage();
      scanner = new Scanner(System.in);
      String input;

      while (true) {

        input = scanner.nextLine();

        if (input.matches(COMMAND_CHECK)) {
          soleProprietor.getBalance();
        } else if (input.matches(COMMAND_PUT)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_PUT, "$2"));
          soleProprietor.putInAccount(indexOfCommand);
        } else if (input.matches(COMMAND_GET)) {
          Double indexOfCommand = Double.parseDouble(input.replaceAll(COMMAND_GET, "$2"));
          soleProprietor.getFromAccount(indexOfCommand);
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
    System.out.println(
        "Вы можете воспользоваться расчетным счетом для физического лица, юридического лица, ИП. ");
    System.out
        .println("1) Для физических лиц, пополнение и снятие со счета происходит без комиссии;");
    System.out.println(
        "2) Для юридических лиц, снятие со счета происходит с комиссией 1% от снимаемой суммы;");
    System.out
        .println("3) Для ИП, при пополнении счета будет взыскиваться комиссия в размере 1%, \n "
            + "если сумма меньше 1000 рублей, и 0,5%, если сумма больше либо равна 1000 рублей;");
    System.out.println(
        "Код доступа к счету физического лица - 0001; Код доступа к счету юридического лица - 0002; Код доступа к счету ИП - 0003;");
    System.out.println("Введите код доступа:  ");
  }
}

