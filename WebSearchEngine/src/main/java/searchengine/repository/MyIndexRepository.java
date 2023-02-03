package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.MyIndex;

@Repository
public interface MyIndexRepository extends JpaRepository<MyIndex, Long> {
    MyIndex findById(Integer id);
}
