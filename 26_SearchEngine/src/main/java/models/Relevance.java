package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


        @Entity
        @Table(name = "relevance")
        public class Relevance implements Serializable{

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

        public Relevance() {}

        public Relevance(Integer id, Integer page, Double absolute_relevance, Double relative_relevance) {
        this.id = id;
        this.page = page;
        this.absolute_relevance = absolute_relevance;
        this.relative_relevance = relative_relevance;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Double getAbsolute_relevance() {
            return absolute_relevance;
        }

        public void setAbsolute_relevance(Double absolute_relevance) {
            this.absolute_relevance = absolute_relevance;
        }

        public Double getRelative_relevance() {
            return relative_relevance;
        }

        public void setRelative_relevance(Double relative_relevance) {
            this.relative_relevance = relative_relevance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Relevance relevance = (Relevance) o;
            return id.equals(relevance.id) && page.equals(relevance.page) && absolute_relevance.equals(relevance.absolute_relevance) && relative_relevance.equals(relevance.relative_relevance);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, page, absolute_relevance, relative_relevance);
        }

        @Override
        public String toString() {
            return "Relevance: {" +
                    "id=" + id +
                    ", page=" + page +
                    ", absolute_relevance=" + absolute_relevance +
                    ", relative_relevance=" + relative_relevance +
                    '}';
        }
}
