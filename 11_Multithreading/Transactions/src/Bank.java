import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {

  private final ConcurrentMap<String, IAccount> accounts;
  private final Random random = new Random();
  AtomicInteger countBlock = new AtomicInteger(0);

  public Bank() {
    this(new ConcurrentHashMap<>());
  }

  public Bank(ConcurrentMap<String, IAccount> accounts) {
    this.accounts = accounts;
  }

  public ConcurrentMap<String, IAccount> getAccounts() {
    return accounts;
  }


  public void bankBuilder() {
    long money = 100000L;
    Long accNumberLong = 1000000000000000001L;
    String accNumber = "";

    for (int i = 0; i < 100; i++) {
      money++;
      accNumberLong++;
      accNumber = accNumberLong.toString();

      Account account = new Account(money, accNumber);
      accounts.put(accNumber, account);
    }
  }

  public void calculateBankBalance() {
    ConcurrentMap<String, IAccount> currentBank = getAccounts();
    long sum = 0;
    for (var pair : currentBank.entrySet()) {
      long value = pair.getValue().getMoney();
      sum += value;
    }
    System.out.println("Общий баланс банка:  " + sum + "  у.е.");
  }


  public synchronized boolean isFraud()
      throws InterruptedException {
    Thread.sleep(1000);
    return random.nextBoolean();
  }


  /**
   * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
   * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
   * метод isFraud. Если возвращается true, то делается блокировка счетов (путем подмены объекта
   * Account, на proxy-объект с измененной функциональностью)
   */
  public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
    // если банк один - можно просто обращаться к полю accounts
    // но если банков несколько - берем экземпляр, получаем его Мапу и с ней работаем
    ConcurrentMap<String, IAccount> currentBank = getAccounts();
    boolean fraud = false;
    if (amount > 50000) {
      try {
        fraud = isFraud();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.err.println("Occurred aborting the transaction");
        return;
      }
    }

    /**
     * перед осуществлением трансфера проверяется - существует ли account с таким номером
     * и не был ли он ранее заменен на proxy-объект
     */
    if (isTransferSupported(fromAccountNum) && isTransferSupported(toAccountNum)) {

      long fromAccountBalance = getBalance(fromAccountNum);
      long toAccountBalance = getBalance(toAccountNum);

      long fromAccountBalanceNew = fromAccountBalance - amount;
      long toAccountBalanceNew = toAccountBalance + amount;

      IAccount fromAccount = currentBank.get(fromAccountNum);
      fromAccount.setMoney(fromAccountBalanceNew);
      currentBank.replace(fromAccountNum, fromAccount);

      IAccount toAccount = currentBank.get(toAccountNum);
      toAccount.setMoney(toAccountBalanceNew);
      currentBank.replace(toAccountNum, toAccount);

      System.out.println("Выполнен перевод со счета " + fromAccountNum + " на счет " + toAccountNum
          + " суммы в размере " + amount);
    } else {
      System.out.println("Операция невозможна - счет заблокирован");
      System.out.println("Произошла " + countBlock.addAndGet(1) + " блокировка трансфера");
    }

    if (fraud) {
      IAccount fromAccount = currentBank.get(fromAccountNum);
      IAccount toAccount = currentBank.get(toAccountNum);
      IAccount fromAccountProxy = (IAccount) Proxy.newProxyInstance(Account.class.getClassLoader(),
          Account.class.getInterfaces(),
          new SubstitutionAccount(fromAccount));

      IAccount toAccountProxy = (IAccount) Proxy.newProxyInstance(Account.class.getClassLoader(),
          Account.class.getInterfaces(),
          new SubstitutionAccount(toAccount));

      currentBank.replace(fromAccountNum, fromAccountProxy);
      currentBank.replace(toAccountNum, toAccountProxy);
    }
  }

  /**
   * Метод проверяет, существует ли счет в банке и если да, не является ли account proxy-объектом
   * (если является - возвращает false)
   */
  public boolean isTransferSupported(String accountNum) {
    IAccount account = accounts.get(accountNum);
    if (account == null) {
      return false;
    }
    return !Proxy.isProxyClass(account.getClass());
  }


  /**
   * Метод возвращает остаток на счёте.
   */
  public synchronized long getBalance(String accountNum) {
    long accountBalance = 0;
    if (accounts.containsKey(accountNum)) {

      accountBalance = accounts.get(accountNum).getMoney();

    } else {
      System.out.println(accountNum + " Указанного номера счета не существует");
    }
    return accountBalance;
  }
}
