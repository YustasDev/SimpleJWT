package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "index")
public class Index {

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

    @Column(name = "rank", nullable = false)
    @NotNull
    private Float rank;

    public Index(){}

    public Index(Integer id, Integer page_id, Integer lemma_id, Float rank) {
        this.id = id;
        this.page_id = page_id;
        this.lemma_id = lemma_id;
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage_id() {
        return page_id;
    }

    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }

    public Integer getLemma_id() {
        return lemma_id;
    }

    public void setLemma_id(Integer lemma_id) {
        this.lemma_id = lemma_id;
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return id.equals(index.id) && page_id.equals(index.page_id) && lemma_id.equals(index.lemma_id) && rank.equals(index.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, page_id, lemma_id, rank);
    }

    @Override
    public String toString() {
        return "Index{" +
                "id= " + id +
                ", page_id= " + page_id +
                ", lemma_id= " + lemma_id +
                ", rank= " + rank +
                '}';
    }
}
