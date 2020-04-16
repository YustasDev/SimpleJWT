package ClientAccount;

import java.math.BigDecimal;

public class LegalPerson extends Client {

  final double COMMISSION = 0.01;

  public LegalPerson(BigDecimal balance) {
    super(balance);
  }

  @Override
  public void getFromAccount(Double indexOfCommand) {
  super.getFromAccount(indexOfCommand * COMMISSION + indexOfCommand);
  }


  public void printUsage() {
    System.out.println("Правила использования расчетного счета юридического лица: ");
    System.out.println(
        "Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
    System.out.println(
        "Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму.\n"
            + " Cнятие со счета происходит с комиссией 1% от снимаемой суммы;");
    System.out.println(
        "Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
    System.out.println("Если Вы хотите прекратить работу со счетом, наберите команду: END");
    System.out.println("Вызовите команду : ");



  }
}
