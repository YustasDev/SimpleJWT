import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank
{
    private ConcurrentHashMap<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ConcurrentHashMap<String, Account> accounts) {
        this.accounts = accounts;
    }


    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(Bank bank, String fromAccountNum, String toAccountNum, long amount) {
        boolean fraud = false;
        if (amount > 50000) {
            try {
                fraud = isFraud(amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("Occurred aborting the transaction");
                return;
            }
        }

        if (!fraud) {
            long fromAccountBalance = bank.getBalance(bank, fromAccountNum);
            long toAccountBalance = bank.getBalance(bank, toAccountNum);

            long fromAccountBalanceNew = fromAccountBalance - amount;
            long toAccountBalanceNew = toAccountBalance + amount;

            // используем метод replace (ключ K, значение V)
            ConcurrentHashMap<String, Account> currentBank = bank.getAccounts();

            Account fromAccount = currentBank.get(fromAccountNum);
            fromAccount.setMoney(fromAccountBalanceNew);
            currentBank.replace(fromAccountNum, fromAccount);

            Account toAccount = currentBank.get(toAccountNum);
            toAccount.setMoney(toAccountBalanceNew);
            currentBank.replace(toAccountNum, toAccount);
        }
        else {
            // 
        }
    }

    /**
     * Метод возвращает остаток на счёте.
     */
    public static long getBalance(Bank bank, String accountNum) {
        ConcurrentHashMap<String, Account> currentBank = bank.getAccounts();
        long accountBalance = 0;
        if (currentBank.containsKey(accountNum)) {

            accountBalance = currentBank.get(accountNum).getMoney();

        } else {
            System.out.println("Указанного номера счета не существует");
        }
        return accountBalance;
    }
}
