import java.lang.reflect.Proxy;
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
  private Bank bank;

  @BeforeClass
  public void setUp() throws Exception {
    Bank bank = new Bank();
    bank.bankBuilder();
    testingBank = bank.getAccounts();
  }

  @Test
  public void getAccounts() {
    int expected = 100;
    int actual = testingBank.size();
    assertEquals("Количество аккаунтов в банке не соответствует требованиям", expected, actual);
  }

  @Test
  public void bankBuilder() {
    assertNotNull(testingBank);
  }

  @Test
  public void calculateBankBalance() {
    long expected = 10005050;
    long actual = bank.calculateBankBalance();
    assertEquals("Неправильный баланс банка", expected, actual);
  }

  @Test
  public void transfer() {
    int numberThreads = 100;
    long startBalance =  bank.calculateBankBalance();
    ExecutorService executorService = Executors
        .newFixedThreadPool(numberThreads);
    for (int i = 0; i < 100; i++) {
      executorService.submit(() ->
          bank.transfer(Main.generatedAccNumber(), Main.generatedAccNumber(), Main
              .generatedMoneyAmount()));
    }
    executorService.shutdown();
    try {
      executorService.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    long finishBalance =  bank.calculateBankBalance();
    assertEquals("Ошибка при проведении трансферов", startBalance, finishBalance);
  }

  @Test
  public void isTransferSupported() {
    String nonexistentAccountNum = "001";
    boolean actual = bank.isTransferSupported(nonexistentAccountNum);
    assertFalse("Ошибка определения несуществующего аккаунта", actual);

    String existentAccountNum = "1000000000000000001";
    Account testAccountProxy = (Account) Proxy.newProxyInstance(Account.class.getClassLoader(),
        Account.class.getInterfaces(),
        new SubstitutionalAccount(existentAccountNum));

    testingBank.replace(existentAccountNum, testAccountProxy);
    boolean actualProxy = bank.isTransferSupported(existentAccountNum);
    assertFalse("Ошибка определения proxy-аккаунта", actualProxy);
  }

  @Test
  public void getBalance() {


  }

  @After
  public void tearDown() throws Exception {
  }
}
