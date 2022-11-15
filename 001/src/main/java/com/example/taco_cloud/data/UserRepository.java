package com.example.taco_cloud.data;

import com.example.taco_cloud.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
        User findByUsername(String username);
    }
