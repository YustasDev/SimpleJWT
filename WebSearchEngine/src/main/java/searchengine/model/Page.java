package searchengine.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "page", indexes = {@Index(columnList = "path", name = "path_indx")})
public class Page implements Serializable{

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

        @Column(name = "site_id", nullable = false)
        @NotNull
        private Integer site_id;







}
