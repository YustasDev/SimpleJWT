package searchengine.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "myIndex")
public class MyIndex implements Serializable {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        private Integer id;

        @Column(name = "page_id", nullable = false)
        @NotNull
        private Integer page_id;

        @Column(name = "lemma_id", nullable = false)
        @NotNull
        private Integer lemma_id;

        @Column(name = "rankOflemma", nullable = false)
        @NotNull
        private Double rankOflemma;


}
