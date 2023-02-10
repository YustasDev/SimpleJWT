package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import searchengine.model.MyIndex;
import searchengine.model.Relevance;

public interface RelevanceRepository extends JpaRepository<Relevance, Long> {
    Relevance findById(Integer id);

    @Modifying
    @Query(value = "truncate table relevance", nativeQuery = true)
    void truncateRelevanceTable();
}

