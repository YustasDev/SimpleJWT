package Accounts;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class DepositaryAccount extends CheckingAccount {

  public ChronoLocalDate timeOfPut = LocalDate.MIN;

  public DepositaryAccount(double balance) {
    super(balance);
  }

  public void putInCheckingAccount(Double indexOfCommand) {
    timeOfPut = LocalDate.now();
    balance += indexOfCommand;
    System.out.println("Вы внесли:  " + indexOfCommand + "рублей");
    System.out.println("На Вашем расчетном счете:  " + balance + "рублей");
  }

  public boolean ensureMonthFromLastDepositElapsed() {
    boolean permit = false;

    LocalDate timeOfGet = null;
    LocalDate date  = null;
    try {
      timeOfGet = LocalDate.now();
      date = timeOfGet.minusMonths(1);
      permit = timeOfPut.isBefore(date);
    } catch (NullPointerException e) {
      e.printStackTrace();
      System.out.println(timeOfGet);
      System.out.println(date);
      System.out.println(timeOfPut);
    }
    return permit;
  }


    public void printUsage () {
      System.out.println("Правила использования Вашего расчетного депозитарного счета: ");
      System.out.println(
          "Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
      System.out.println(
          "Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму. "
              + "\n"
              + "Учтите, что деньги можно снять не ранее, чем через месяц после последнего внесения");
      System.out.println(
          "Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
      System.out.println("Если Вы хотите прекратить работу со счетом, наберите команду: END");
      System.out.println("Вызовите команду : ");
    }
  }