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
@Table(name = "relevance")
public class Relevance implements Serializable, Comparable<Relevance> {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        private Integer id;


        @Column(name = "page", nullable = false)
        @NotNull
        private Integer page;

        @Column(name = "absolute_relevance", nullable = false)
        @NotNull
        private Double absolute_relevance;

        @Column(name = "relative_relevance", nullable = false)
        @NotNull
        private Double relative_relevance;

        @Override
        public int compareTo(Relevance relevance) {
            return (int) (relevance.getAbsolute_relevance() - this.getAbsolute_relevance());
        }
}
