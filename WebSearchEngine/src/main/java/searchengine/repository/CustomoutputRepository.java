package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import searchengine.model.CustomOutput;
import searchengine.model.Relevance;

import java.util.List;

public interface CustomoutputRepository extends JpaRepository<CustomOutput, Long> {
    CustomOutput findById(Integer id);

    @Modifying
    @Query(value = "truncate table customoutput", nativeQuery = true)
    void truncateCustomoutputTable();

    @Query("SELECT cop FROM CustomOutput cop WHERE cop.id IN(SELECT MAX(cop.id) AS mid FROM CustomOutput cop group by cop.uri)")
    List<CustomOutput> customoutputList ();



}
