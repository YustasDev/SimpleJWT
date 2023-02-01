package searchengine.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "page", indexes = {@Index(columnList = "path", name = "path_indx")})
public class Page implements Serializable{

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        private Integer id;

        @Column(name = "path", columnDefinition = "varchar(512)", nullable = false)
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

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name="site_id", nullable=false)
        private SiteModel site;

        public Page(String url, int code, String content, String lemmatized_content, SiteModel site) {
                this.path = url;
                this.code = code;
                this.content = content;
                this.lemmatized_content = lemmatized_content;
                this.site = site;
        }
}
