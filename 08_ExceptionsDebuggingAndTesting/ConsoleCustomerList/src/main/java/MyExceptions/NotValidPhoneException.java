package MyExceptions;

public class NotValidPhoneException extends RuntimeException {

  private String phone;

  public String get_Phone() {
    return phone;
  }

  public NotValidPhoneException(String message, String phone) {
    super(message);
    this.phone = phone;
    System.err.println(phone);
  }
}
