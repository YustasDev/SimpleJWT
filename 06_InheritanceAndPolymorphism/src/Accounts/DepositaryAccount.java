package Accounts;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

public class DepositaryAccount extends CheckingAccount {

  public ChronoLocalDate timeOfPut;
  public int x = 0;

  public DepositaryAccount(double balance) {
    super(balance);
  }

  public void run() {
    super.run();
  }

  public void putInCheckingAccount(String input) {
    super.putInCheckingAccount(input);
    LocalDate timeOfPut = LocalDate.now();
    int x = 8;
  }

  public void getFromCheckingAccount(String input) {
    boolean permit = false;
    LocalDate timeOfGet = null;
    LocalDate date = null;
    try {
      timeOfGet = LocalDate.now();
      date = timeOfGet.minusMonths(1);
      permit = timeOfPut.isBefore(date);

      if (permit) {
        super.getFromCheckingAccount(input);
      } else {
        System.out.println(
            "Операция не может быть выполнена. Внесение денежных средств было осуществлено менее месяца назад");
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
      System.out.println(timeOfGet);
      System.out.println(date);
      System.out.println(timeOfPut);
      System.out.println(x);
    }
  }

    public void printUsage() {
    System.out.println("Правила использования Вашего расчетного депозитарного счета: ");
    System.out.println("Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
    System.out.println("Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму. "
        + "\n" + "Учтите, что деньги можно снять не ранее, чем через месяц после последнего внесения");
    System.out.println("Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
    System.out.println("Если Вы хотите прекратить работу со счетом, наберите команду: END");
    System.out.println("Вызовите команду : ");
  }
}