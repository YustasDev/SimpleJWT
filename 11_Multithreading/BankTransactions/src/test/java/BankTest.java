import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;
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
    String existentAccountNum = "1000000000000000001";
    testBank.lockAccount(existentAccountNum);
    assertFalse("Ошибка определения несуществующего или заблокированного аккаунта",
        testBank.isTransferSupported(existentAccountNum));
  }


  @Test(expected = AccountIsNotExistException.class)
  public void testGetBalanceforNotExistAccount() throws AccountIsNotExistException {
    for (Long accountNumLong = 1000000000000000101L; accountNumLong < 1000000000000000110L;
        accountNumLong++) {
      String accounNum = accountNumLong.toString();
      try {
        testBank.getBalance(accounNum);
        fail("Exception expected");
      } catch (AccountIsNotExistException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void tearDown() throws Exception {
  }
}
