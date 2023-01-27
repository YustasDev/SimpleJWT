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

        @Column(name = "snippet", columnDefinition = "MEDIUMTEXT", nullable = false)
        @Type( type = "org.hibernate.type.TextType")
        @NotNull
        private String snippet;

        @Column(name = "relevance", nullable = false)
        @NotNull
        private Double relevance;
}
