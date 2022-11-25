package models;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "page", indexes = {@Index(columnList = "path", name = "path_indx")})
public class Page implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private Integer id;

  @Column(name = "path", columnDefinition = "TEXT", nullable = false)
  @Type( type = "org.hibernate.type.TextType")
  @NotNull
  private String path;

  @Column(name = "code", nullable = false)
  @NotNull
  private Integer code;

  @Column(name = "content", columnDefinition = "MEDIUMTEXT", nullable = false)
  @Type( type = "org.hibernate.type.TextType")
  @NotNull
  private String content;

  @Column(name = "lemmatized_content", columnDefinition = "MEDIUMTEXT", nullable = false)
  @Type( type = "org.hibernate.type.TextType")
  @NotNull
  private String lemmatized_content;


  public Page(){}

  public Page(String path, int code, String content, String lemmatized_content) {
    this.path = path;
    this.code = code;
    this.content = content;
    this.lemmatized_content = lemmatized_content;
  }

  public Page(Integer id, String path, Integer code, String content, String lemmatized_content) {
    this.id = id;
    this.path = path;
    this.code = code;
    this.content = content;
    this.lemmatized_content = lemmatized_content;
  }

  public Integer getId() {
    return id;
  }

  private void setId(Integer id) {
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

  public String getLemmatized_content() { return lemmatized_content;}

  public void setLemmatized_content(String lemmatized_content) {this.lemmatized_content = lemmatized_content;}

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
    return "models.Page is {" +
        "content=' " + content + '\'' +
        ", path=' " + path + '\'' +
        ", code= " + code +
        ", lemmatized_content= " + lemmatized_content +
        '}';
  }

}
