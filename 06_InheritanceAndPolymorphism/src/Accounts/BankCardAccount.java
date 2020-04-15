package Accounts;

public class BankCardAccount extends Account {

  final double COMMISSION = 0.01;

  public BankCardAccount(double balance) {
    super(balance);
  }

  public void getFromAccount(Double indexOfCommand) {
    super.getFromAccount(indexOfCommand * COMMISSION + indexOfCommand);
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



