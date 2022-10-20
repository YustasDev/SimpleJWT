package models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "customoutput")
public class CustomOutput implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @Column(name = "uri", nullable = false)
    @NotNull
    private String uri;

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "content", columnDefinition = "MEDIUMTEXT", nullable = false)
    @Type( type = "org.hibernate.type.TextType")
    @NotNull
    private String snippet;

    @Column(name = "relevance", nullable = false)
    @NotNull
    private Double relevance;

    public CustomOutput() {
    }

    public CustomOutput(Integer id, String uri, String title, String snippet, Double relevance) {
        this.id = id;
        this.uri = uri;
        this.title = title;
        this.snippet = snippet;
        this.relevance = relevance;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomOutput that = (CustomOutput) o;
        return id.equals(that.id) && uri.equals(that.uri) && title.equals(that.title) && snippet.equals(that.snippet) && relevance.equals(that.relevance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uri, title, snippet, relevance);
    }

    @Override
    public String toString() {
        return "CustomOutput: {" +
                "id= " + id +
                ", uri= '" + uri + '\'' +
                ", title= '" + title + '\'' +
                ", snippet= '" + snippet + '\'' +
                ", relevance= " + relevance +
                '}';
    }

}
