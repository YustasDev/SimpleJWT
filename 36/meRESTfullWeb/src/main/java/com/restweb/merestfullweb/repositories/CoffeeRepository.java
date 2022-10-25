package com.restweb.merestfullweb.repositories;

import com.restweb.merestfullweb.models.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, String> {}
