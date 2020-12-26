import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

  public static void main(String[] args) {

    long money = 1L;
    Long accNumberLong = 1000000000000000001L;
    String accNumber = "";
    ConcurrentHashMap<String, Account> count = new ConcurrentHashMap<>();

    Bank bank = new Bank();

    for (int i = 0; i < 100; i++)
    {
      money++;
      accNumberLong++;
      accNumber = accNumberLong.toString();

      Account account = new Account(money, accNumber);
      count.put(accNumber, account);

      bank.setAccounts(count);
    }

    ConcurrentHashMap<String, Account> currentBank = bank.getAccounts();

    for(var pair: currentBank.entrySet())
    {
      String key = pair.getKey();
      Account value = pair.getValue();
      System.out.println(key + "  " + value.toString());
    }

    String accountNum = "1000000000000000068";
    long balance = bank.getBalance(bank, accountNum);
    System.out.println("На счете № " + accountNum + " остаток средств составляет '" + balance + "'");
  }

}
