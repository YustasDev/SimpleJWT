import java.util.Objects;

public class Page {

  String content;
  String path;
  Integer code;

  public Page(String path, Integer code, String content) {
    this.path = path;
    this.code = code;
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public String getPath() {
    return path;
  }

  public Integer getCode() {
    return code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Page)) {
      return false;
    }
    Page page = (Page) o;
    return content.equals(page.content) &&
        path.equals(page.path) &&
        code.equals(page.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, path, code);
  }

  @Override
  public String toString() {
    return "Page is {" +
        "content='" + content + '\'' +
        ", path='" + path + '\'' +
        ", code=" + code +
        '}';
  }

}
