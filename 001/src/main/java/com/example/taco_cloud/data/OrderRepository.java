package com.example.taco_cloud.data;

import com.example.taco_cloud.dto.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
      //  TacoOrder save(TacoOrder order);
    }

