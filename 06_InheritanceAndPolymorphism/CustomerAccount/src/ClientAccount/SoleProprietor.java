package ClientAccount;

import java.math.BigDecimal;

public class SoleProprietor extends Client {

  final double COMMISSION_1_PERCENT = 0.01;
  final double COMMISSION_05_PERCENT = 0.005;

  public SoleProprietor(BigDecimal balance) {
    super(balance);
  }

  @Override
  public void putInAccount(Double indexOfCommand) {
    if (indexOfCommand < 1000.0) {
      super.putInAccount(indexOfCommand - indexOfCommand * COMMISSION_1_PERCENT);
    } else {
      super.putInAccount(indexOfCommand - indexOfCommand * COMMISSION_05_PERCENT);
    }
  }

  public void printUsage() {
    System.out.println("Правила использования Вашего расчетного счета: ");
    System.out.println(
        "Если Вы хотите внести деньги, наберите команду: PUT и через пробел вносимую сумму");
    System.out.println("При пополнении счета будет взыскиваться комиссия в размере 1%, \n "
        + "если сумма меньше 1000 рублей, и 0,5%, если сумма больше либо равна 1000 рублей");
    System.out.println(
        "Если Вы хотите снять деньги, наберите команду: GET и через пробел снимаемую сумму");
    System.out.println(
        "Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: CHECK");
    System.out.println("Если Вы хотите прекратить работу со счетом, наберите команду: END");
    System.out.println("Вызовите команду : ");
  }

}




