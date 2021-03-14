

public class DuringParseException extends RuntimeException {

  private String url;

  public String get_Url() {
    return url;
  }

  public DuringParseException(String message, String url) {
    super(message);
    this.url = url;
    System.err.println(url);
  }
}
