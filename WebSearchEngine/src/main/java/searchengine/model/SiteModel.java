package searchengine.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "site")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteModel implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED', 'FAILED')", nullable = false)
    @NotNull
    private Status status;

    @Basic
    @Column(name = "status_time", updatable = false, nullable = false)
    @NotNull
    private LocalDateTime status_time;

    @Column(name = "last_error", columnDefinition = "TEXT")
    @Type( type = "org.hibernate.type.TextType")
    private String last_error;

    @Column(name = "url", nullable = false)
    @NotNull
    private String url;

    @Column(name = "site_name", nullable = false)
    @NotNull
    private String site_name;



    public enum Status {
        INDEXING,
        INDEXED,
        FAILED;
    }
}
