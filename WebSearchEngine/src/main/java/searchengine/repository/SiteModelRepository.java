package searchengine.repository;

import org.springframework.data.repository.CrudRepository;
import searchengine.model.SiteModel;

import java.util.List;

public interface SiteModelRepository extends CrudRepository<SiteModel, Long> {
    List<SiteModel> findById(Integer id);
}
