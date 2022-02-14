import com.sun.istack.NotNull;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "page", indexes = {@Index(columnList = "path", name = "path_indx")})
public class Page {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private Integer id;

  @Column(name = "path", columnDefinition = "TEXT", nullable = false)
  @Type( type = "org.hibernate.type.TextType")
  @NotNull
  String path;

  @Column(name = "code", nullable = false)
  @NotNull
  Integer code;

  @Column(name = "content", columnDefinition = "MEDIUMTEXT", nullable = false)
  @Type( type = "org.hibernate.type.TextType")
  @NotNull
  String content;

  public Page(){}

  public Page(String path, int code, String content) {
    this.path = path;
    this.code = code;
    this.content = content;
  }

  public Page(Integer id, String path, Integer code, String content) {
    this.id = id;
    this.path = path;
    this.code = code;
    this.content = content;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
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
