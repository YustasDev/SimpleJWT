package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import searchengine.model.MyIndex;

import java.util.List;

@Repository
public interface MyIndexRepository extends JpaRepository<MyIndex, Long> {
    MyIndex findById(Integer id);

    @Query("SELECT mI FROM MyIndex mI WHERE mI.lemma_id = ?1")
    List<MyIndex> findByLemma_Id(Integer lemma_id);

    @Query("SELECT SUM (mi.rankOflemma) FROM MyIndex mI WHERE mI.page_id = ?1")
    Double findSumRankOfLemmaByPageId(Integer numberPage);
}
