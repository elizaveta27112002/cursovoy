package com.example.cursovoy.repo;

import com.example.cursovoy.models.User;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

