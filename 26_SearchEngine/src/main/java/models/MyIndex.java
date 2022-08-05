package models;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

        public MyIndex(){}

        public MyIndex(Integer id, Integer page_id, Integer lemma_id, Double rankOflemma) {
            this.id = id;
            this.page_id = page_id;
            this.lemma_id = lemma_id;
            this.rankOflemma = rankOflemma;
        }

        public Integer getId() {
            return id;
        }

        private void setId(Integer id) {
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

        public Double getRankOflemma() {
            return rankOflemma;
        }

        public void setRankOflemma(Double rankOflemma) {
            this.rankOflemma = rankOflemma;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyIndex myIndex = (MyIndex) o;
            return id.equals(myIndex.id) && page_id.equals(myIndex.page_id) && lemma_id.equals(myIndex.lemma_id) && rankOflemma.equals(myIndex.rankOflemma);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, page_id, lemma_id, rankOflemma);
        }

        @Override
        public String toString() {
            return "Field{" +
                    "id= " + id +
                    ", page_id= '" + page_id + '\'' +
                    ", selector=' " + lemma_id + '\'' +
                    ", rankOflemma= " + rankOflemma +
                    '}';
        }
    }

