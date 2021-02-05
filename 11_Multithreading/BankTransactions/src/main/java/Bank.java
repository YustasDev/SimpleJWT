import static java.util.Objects.requireNonNull;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

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
    boolean locked = false;

    for (int i = 0; i < 100; i++) {
      money++;
      accNumberLong++;
      accNumber = accNumberLong.toString();

      PersonalAccount account = new PersonalAccount(money, accNumber, locked);
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

  private Account ensureAccount(String accNumber) {
    Account account = accounts.get(accNumber);
    return requireNonNull(account, "account with number " + accNumber + " doesn't exists");
  }

  private static void ensureAmount(long amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount (" + amount + ") should be greater then zero");
    }
  }

  /**
   * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
   * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
   * метод isFraud. Если возвращается true, то делается блокировка счетов. Перед осуществлением
   * трансфера проверяется: 1) существует ли account с таким номером; 2) нет ли попытки перевести
   * деньги самому себе с одного и того же счета
   */
  public void transfer(String fromAccountNum, String toAccountNum, long amount) {
    if (fromAccountNum.equals(toAccountNum)) {
      return;
    }

    requireNonNull(fromAccountNum, "fromAccountNum can't be null");
    requireNonNull(toAccountNum, "toAccountNum can't be null");
    ensureAmount(amount);

    Account fromAccount = ensureAccount(fromAccountNum);
    Account toAccount = ensureAccount(toAccountNum);

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

    Account lowLock = fromAccountNum.compareTo(toAccountNum) < 0 ? fromAccount : toAccount;
    Account highLock = fromAccountNum.compareTo(toAccountNum) < 0 ? toAccount : fromAccount;

    synchronized (lowLock) {
      if (lowLock.isLocked()) {
        printAccountIsBlocked();
        return;
      }
      synchronized (highLock) {
        if (highLock.isLocked()) {
          printAccountIsBlocked();
          return;
        }
        long fromAccountBalance = fromAccount.getMoney();
        long toAccountBalance = toAccount.getMoney();

        long fromAccountBalanceNew = fromAccountBalance - amount;
        fromAccount.setMoney(fromAccountBalanceNew);

        long toAccountBalanceNew = toAccountBalance + amount;
        toAccount.setMoney(toAccountBalanceNew);

        if (fraud) {
          fromAccount.setLocked(true);
          toAccount.setLocked(true);
        }
      }
    }

    System.out
        .println("Выполнен перевод со счета " + fromAccountNum + " на счет " + toAccountNum
            + " суммы в размере " + amount);
  }

  /**
   * Метод проверяет, существует ли счет в банке и если существует, не заблокирован ли он
   */
  public boolean isTransferSupported(String accountNum) {
    Account account = accounts.get(accountNum);
    if (account != null) {
      synchronized (account) {
        return !account.isLocked();
      }
    }
    return false;
  }

  public void lockAccount(String accountNum) {
    Account account = accounts.get(accountNum);
    if (account != null) {
      synchronized (account) {
        account.setLocked(true);
      }
    }
  }

  public void printAccountIsBlocked() {
    System.out.println("Операция невозможна - счет заблокирован");
    System.out.println("Произошла " + countBlock.addAndGet(1) + " блокировка трансфера");
  }

  /**
   * Метод возвращает остаток на счёте, если счета не существует, бросаем
   * AccountIsNotExistException, который на это указывает
   *
   * @return
   */
  public synchronized long getBalance(String accountNum) throws AccountIsNotExistException {
    long accountBalance = 0;
    if (accounts.containsKey(accountNum)) {
      accountBalance = accounts.get(accountNum).getMoney();
    } else {
      throw new AccountIsNotExistException("Указанного номера счета не существует ", accountNum);
    }
    return accountBalance;
  }
}
