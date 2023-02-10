package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.Lemma;

import java.util.List;

@Repository
public interface LemmaRepository extends JpaRepository<Lemma, Long> {
    List<Lemma> findAll();
    Lemma findByLemma(String lemma);

    @Query("SELECT l FROM Lemma l WHERE l.lemma LIKE ?1%")
    List<Lemma> findByLemmaStartsWith(String newKey);
}
