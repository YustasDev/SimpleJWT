package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import searchengine.model.Lemma;

import java.util.List;

public interface LemmaRepository extends JpaRepository<Lemma, Long> {
    List<Lemma> findAll();
    Lemma findByLemma(String lemma);
}
