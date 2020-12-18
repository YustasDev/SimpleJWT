

public class FileIsNotImageException extends RuntimeException {

  private String fileName;
  public String getFileName(){return fileName;}

  public FileIsNotImageException(String message, String fileName){
    super(message);
    this.fileName = fileName;
    System.err.println(fileName);
  }
}



