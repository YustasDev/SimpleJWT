

public class AccountIsNotExistException extends RuntimeException {

    private String accountNum;
    public String getAccountNum(){return accountNum;}

    public AccountIsNotExistException(String message, String accountNum){
      super(message);
      this.accountNum = accountNum;
      System.err.println(accountNum);
    }
  }
