import java.io.IOException;

public class Loader {

  public static void main(String[] args) throws IOException {

    final String COMMAND_TENDER = "(?i)(TENDER\\s+)(\\d+)";
    final String COMMAND_WITHDROW = "(?i)(WITHDROW\\s+)(\\d+)";
    final String COMMAND_LIST = "(?i)DEPOSIT";
    Double account;
    String command = null;

    printUsage();




  }

  public static void printUsage() {
    System.out.println("Чтобы использовать Ваш расчетный счет, введите код доступа [первоначальный код доступа - 0000]");
    System.out.println("Если Вы хотите внести деньги, наберите команду: TENDER и через пробел вносимую сумму");
    System.out.println("Если Вы хотите снять деньги, наберите команду: WITHDROW и через пробел снимаемую сумму");
    System.out.println("Если Вы хотите проверить, сколько денег на расчетном счёте, наберите команду: DEPOSIT");
    System.out.println("Вызовите команду : ");
  }

}
