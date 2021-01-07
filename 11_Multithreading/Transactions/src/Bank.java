import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    private ConcurrentHashMap<String, IAccount> accounts;
    private final Random random = new Random();

    public ConcurrentHashMap<String, IAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(ConcurrentHashMap<String, IAccount> accounts) {
        this.accounts = accounts;
    }

    public void bankBuilder () {
        long money = 1L;
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

    public synchronized boolean isFraud()
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }


    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (путем подмены объекта Account, на proxy-объект с измененной функциональностью)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        ConcurrentHashMap<String, IAccount> currentBank = getAccounts();
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

            long fromAccountBalance = getBalance(fromAccountNum);
            long toAccountBalance = getBalance(toAccountNum);

            long fromAccountBalanceNew = fromAccountBalance - amount;
            long toAccountBalanceNew = toAccountBalance + amount;

            // если банк один - можно просто обращаться к полю accounts
            // но если банков несколько - берем экземпляр, получаем его Мапу и с ней работаем
            //ConcurrentHashMap<String, IAccount> currentBank = getAccounts();

            IAccount fromAccount = currentBank.get(fromAccountNum);
            fromAccount.setMoney(fromAccountBalanceNew);
            currentBank.replace(fromAccountNum, fromAccount);

            IAccount toAccount = currentBank.get(toAccountNum);
            toAccount.setMoney(toAccountBalanceNew);
            currentBank.replace(toAccountNum, toAccount);

        if (fraud) {
            IAccount accountProxy = (IAccount) Proxy.newProxyInstance(Account.class.getClassLoader(),
                Account.class.getInterfaces(),
                new SubstitutionAccount(fromAccount));
            currentBank.replace(fromAccountNum, accountProxy);
            currentBank.replace(toAccountNum, accountProxy);
        }
    }

    /**
     * Метод возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        long accountBalance = 0;
        if (accounts.containsKey(accountNum)) {

            accountBalance = accounts.get(accountNum).getMoney();

        } else {
            System.out.println("Указанного номера счета не существует");
        }
        return accountBalance;
    }
}
