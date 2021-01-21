import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankTest extends TestCase {

  private ConcurrentMap<String, Account> testingBank;
  private Bank testBank = new Bank();

  @Override
  public void setUp() throws Exception {
    testBank.bankBuilder();
    testingBank = testBank.getAccounts();
  }

  @Test
  public void testGetAccounts() {
    int expected = 100;
    int actual = testingBank.size();
    assertEquals("Количество аккаунтов в банке не соответствует требованиям", expected, actual);
  }

  @Test
  public void testBankBuilder() {
    assertNotNull(testingBank);
  }

  @Test
  public void testCalculateBankBalance() {
    long expected = 10005050;
    long actual = testBank.calculateBankBalance();
    assertEquals("Неправильный баланс банка", expected, actual);
  }

  @Test
  public void testTransfer() {
    int numberThreads = 100;
    long startBalance = testBank.calculateBankBalance();
    ExecutorService executorService = Executors
        .newFixedThreadPool(numberThreads);
    for (int i = 0; i < 100; i++) {
      executorService.submit(() ->
          testBank.transfer(Main.generatedAccNumber(), Main.generatedAccNumber(), Main
              .generatedMoneyAmount()));
    }
    executorService.shutdown();
    try {
      executorService.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    long finishBalance = testBank.calculateBankBalance();
    assertEquals("Ошибка при проведении трансферов", startBalance, finishBalance);
  }

  @Test
  public void testIsTransferSupported() {
    String nonexistentAccountNum = "001";
    boolean actual = testBank.isTransferSupported(nonexistentAccountNum);
    assertFalse("Ошибка определения несуществующего аккаунта", actual);

    String existentAccountNum = "1000000000000000001";
    Account existentAccount = testingBank.get(existentAccountNum);

    Account testAccountProxy = (Account) Proxy.newProxyInstance(Account.class.getClassLoader(),
        Account.class.getInterfaces(),
        new SubstitutionalAccount(existentAccount));

    testingBank.replace(existentAccountNum, testAccountProxy);
    boolean actualProxy = testBank.isTransferSupported(existentAccountNum);
    assertFalse("Ошибка определения proxy-аккаунта", actualProxy);
  }

  @Test
  public void testGetBalance() {
    for (Long accountNumLong = 1000000000000000000L; accountNumLong < 1000000000000000110L;
        accountNumLong++) {
      String accounNum = accountNumLong.toString();
      assertNotNull("Неправильное вычисление баланса аккаунта", testBank.getBalance(accounNum));
    }
  }

  @Override
  public void tearDown() throws Exception {
  }
}
