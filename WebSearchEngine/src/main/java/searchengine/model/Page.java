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


        @ManyToOne
        @JoinColumn(name="site_id", nullable=false)
        private Site site;

}
