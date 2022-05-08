package exception;


public class DuringParseException extends RuntimeException {

  private String url;

  public String get_Url() {
    return url;
  }

  public DuringParseException(String message, Exception e, String url) {
    super(message, e);
    this.url = url;
    System.err.println(url);
  }
}
