package main.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends CrudRepository<CriminalCase, Integer> {

    List<CriminalCase> findByDescription(String str);



}