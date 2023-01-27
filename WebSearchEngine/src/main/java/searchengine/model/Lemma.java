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
@Table(name = "lemma")
public class Lemma implements Serializable {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        private Integer id;

        @Column(name = "lemma", nullable = false)
        @NotNull
        private String lemma;

        @Column(name = "frequency", nullable = false)
        @NotNull
        private Integer frequency;






}
