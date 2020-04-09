import Accounts.BankCardAccount;
import Accounts.CheckingAccount;
import Accounts.DepositaryAccount;
import java.io.IOException;
import java.util.Scanner;


public class Loader {


  public static void main(String[] args) throws IOException {

    System.out.println("В Вашем распоряжении имеется:  ");
    System.out.println("1) Расчетный счет, на который можно положить или снять денежные средства");
    System.out.println(
        "2) Расчетный депозитарный счет, с которого нельзя снимать деньги в течение месяца после последнего внесения");
    System.out.println(
        "3) Карточный счет, при снятии денег с которого будет взиматься комиссия в размере 1%");
    System.out.println(
        "Код доступа к расчетному счету - 0001; Код доступа к расчетному депозитарному счету - 0002; Код доступа к карточному счету - 0003");
    System.out.println("Введите код доступа:  ");

    Scanner scanner = new Scanner(System.in);
    String access;

    access = scanner.nextLine();
    if (access.equals("0001")) {
      CheckingAccount checkingAccount = new CheckingAccount();
      checkingAccount.run();
    } else if (access.equals("0002")) {
      DepositaryAccount depositaryAccountAccount = new DepositaryAccount();
      depositaryAccountAccount.run();
    } else if (access.equals("0003")) {
      BankCardAccount bankCardAccount = new BankCardAccount();
      bankCardAccount.run();
    }
      else {
      System.out.println("Код доступа не распознан, доступ запрещен");
      }
    }
  }
