
import java.util.concurrent.ConcurrentHashMap;

public class Main {

  public static void main(String[] args) {

    Bank bank = new Bank();
    bank.setAccounts(new ConcurrentHashMap<>());
    bank.bankBuilder();

    ConcurrentHashMap<String, Account> currentBank = bank.getAccounts();

    for(var pair: currentBank.entrySet())
    {
      String key = pair.getKey();
      Account value = pair.getValue();
      System.out.println(key + "  " + value.toString());
    }

    bank.transfer("1000000000000000068", "1000000000000000069", 100000);

    String accountNum68 = "1000000000000000068";
    long balance68 = bank.getBalance(accountNum68);
    System.out.println("На счете № " + accountNum68 + " остаток средств составляет '" + balance68 + "'");

    String accountNum69 = "1000000000000000069";
    long balance69 = bank.getBalance(accountNum69);
    System.out.println("На счете № " + accountNum69 + " остаток средств составляет '" + balance69 + "'");






  }

}
