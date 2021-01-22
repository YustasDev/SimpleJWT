import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

  private final ConcurrentMap<String, Account> accounts;
  private final Random random = new Random();
  AtomicInteger countBlock = new AtomicInteger(0);

  public Bank() {
    this(new ConcurrentHashMap<>());
  }

  public Bank(ConcurrentMap<String, Account> accounts) {
    this.accounts = accounts;
  }

  public ConcurrentMap<String, Account> getAccounts() {
    return accounts;
  }


  public void bankBuilder() {
    long money = 100000L;
    Long accNumberLong = 1000000000000000000L;
    String accNumber = "";

    for (int i = 0; i < 100; i++) {
      money++;
      accNumberLong++;
      accNumber = accNumberLong.toString();

      PersonalAccount account = new PersonalAccount(money, accNumber);
      accounts.put(accNumber, account);
    }
  }

  public long calculateBankBalance() {
    ConcurrentMap<String, Account> currentBank = getAccounts();
    long sum = 0;
    for (var pair : currentBank.entrySet()) {
      long value = pair.getValue().getMoney();
      sum += value;
    }
    System.out.println("Общий баланс банка:  " + sum + "  у.е.");
    return sum;
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
  public void transfer(String fromAccountNum, String toAccountNum, long amount) {
    Account fromAccount = accounts.get(fromAccountNum);
    Account toAccount = accounts.get(toAccountNum);

    ConcurrentMap<String, Account> currentBank = getAccounts();
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
     * перед осуществлением трансфера проверяется: 1) существует ли account с таким номером;
     * 2) не был ли он ранее заменен на proxy-объект;
     * 3) нет ли попытки перевести деньги самому себе с одного и того же счета
     */
    if (isTransferSupported(fromAccountNum) && isTransferSupported(toAccountNum) && !(fromAccountNum
        .equals(toAccountNum))) {

      if (fromAccountNum.compareTo(toAccountNum) < 0) {
        synchronized (fromAccount) {
          synchronized (toAccount) {
            long fromAccountBalance = getBalance(fromAccountNum);
            long toAccountBalance = getBalance(toAccountNum);

            long fromAccountBalanceNew = fromAccountBalance - amount;
            long toAccountBalanceNew = toAccountBalance + amount;

            fromAccount.setMoney(fromAccountBalanceNew);
            toAccount.setMoney(toAccountBalanceNew);
          }
        }
      }
      else {
        synchronized (toAccount) {
          synchronized (fromAccount) {
            long fromAccountBalance = getBalance(fromAccountNum);
            long toAccountBalance = getBalance(toAccountNum);

            long fromAccountBalanceNew = fromAccountBalance - amount;
            long toAccountBalanceNew = toAccountBalance + amount;

            fromAccount.setMoney(fromAccountBalanceNew);
            toAccount.setMoney(toAccountBalanceNew);
          }
        }
      }

        System.out
            .println("Выполнен перевод со счета " + fromAccountNum + " на счет " + toAccountNum
                + " суммы в размере " + amount);
      } else {
        System.out.println("Операция невозможна - счет заблокирован");
        System.out.println("Произошла " + countBlock.addAndGet(1) + " блокировка трансфера");
      }

      if (fraud) {
        Account fromAccountProxy = (Account) Proxy.newProxyInstance(PersonalAccount.class.getClassLoader(),
            PersonalAccount.class.getInterfaces(),
            new SubstitutionalAccount(fromAccount));

        Account toAccountProxy = (Account) Proxy.newProxyInstance(PersonalAccount.class.getClassLoader(),
            PersonalAccount.class.getInterfaces(),
            new SubstitutionalAccount(toAccount));

        accounts.replace(fromAccountNum, fromAccountProxy);
        accounts.replace(toAccountNum, toAccountProxy);
      }
    }

    /**
   * Метод проверяет, существует ли счет в банке и если да, не является ли account proxy-объектом
   * (если является - возвращает false)
   */
  public boolean isTransferSupported(String accountNum) {
    Account account = accounts.get(accountNum);
    if (account == null) {
      return false;
    }
    return !Proxy.isProxyClass(account.getClass());
  }


  /**
   * Метод возвращает остаток на счёте.
   * @return
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
