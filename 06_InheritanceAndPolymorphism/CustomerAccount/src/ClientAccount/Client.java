package ClientAccount;

import java.math.BigDecimal;

abstract class Client {

  BigDecimal balance;

  Client (BigDecimal balance) {
    this.balance = balance;
  }

  protected Client() {
  }

  public BigDecimal getBalance() {
    System.out.println("На Вашем расчетном счете:  " + balance + " рублей");
    return balance;
  }

  public void putInAccount(Double indexOfCommand) {
    balance = balance.add(BigDecimal.valueOf(indexOfCommand));
    System.out.println("Вы внесли:  " + indexOfCommand + " рублей");
    System.out.println("На Вашем расчетном счете:  " + balance + " рублей");
  }


  public void getFromAccount(Double indexOfCommand) {
    balance = balance.subtract(BigDecimal.valueOf(indexOfCommand));
    int checkingForZero = balance.compareTo(BigDecimal.ZERO);
    if (checkingForZero >= 0) {
      System.out.println("Вы сняли:  " + indexOfCommand + " рублей");
      System.out.println("На Вашем расчетном счете:  " + balance + "  рублей");
    } else {
      balance = balance.add(BigDecimal.valueOf(indexOfCommand));
      System.out.println("Указанная Вами сумма " + indexOfCommand + " рублей, больше размера депозита");
      System.out.println("Операция не может быть выполнена. На Вашем расчетном счете:  " + balance + " рублей");
    }
  }
  }

