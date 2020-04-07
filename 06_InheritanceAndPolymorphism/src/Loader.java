import Accounts.CheckingAccount;
import java.io.IOException;
import java.util.Scanner;


public class Loader {


  public static void main(String[] args) throws IOException {

    CheckingAccount checkingAccount = new CheckingAccount();
    checkingAccount.run();
  }


}
