package MyExceptions;

public class NotValidNameException extends RuntimeException {

  private String name;
  public String get_Name(){return name;}

  public NotValidNameException(String message, String name){
    super(message);
    this.name = name;
    System.err.println(name);
  }
}

