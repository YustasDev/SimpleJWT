package Accounts;

public class BankCardAccount extends CheckingAccount {

  final double COMMISSION = 1.01;

  public BankCardAccount(double balance) {
    super(balance);
  }

  public void getFromCheckingAccount(Double indexOfCommand) {
    balance = balance - (indexOfCommand * COMMISSION);
    if (balance >= 0){
      System.out.println("Снято:  " + indexOfCommand + "  комиссия  " + (indexOfCommand * 0.01) + "рублей");
      System.out.println("На Вашем расчетном счете:  " + balance + "рублей");
    } else{
      balance += indexOfCommand * COMMISSION;
      System.out.println("Указанная Вами сумма " + indexOfCommand + "рублей, больше размера депозита");
      System.out.println("Операция не может быть выполнена. На Вашем расчетном счете:  " + balance + "рублей");
    }
  }




  public void printUsage() {
    System.out.println("Правила использования Вашего банковского карточного счета: ");
    System.out.println(
        "Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
    System.out.println(
        "Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму. "
            + "\n"
            + "Учтите, что при снятии денег с карточного счета будет взиматься комиссия в размере 1%);");
    System.out.println(
        "Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
    System.out.println("Если Вы хотите прекратить работу со счетом, наберите команду: END");
    System.out.println("Вызовите команду : ");
  }
}



