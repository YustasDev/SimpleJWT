package MyExceptions;

public class NotValidEmailException extends RuntimeException {

  private String eMail;
  public String get_eMail(){return eMail;}

  public NotValidEmailException(String message, String eMail){
    super(message);
    this.eMail = eMail;
    System.err.println(eMail);
  }
}
