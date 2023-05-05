package com.example.database.repository;

import com.example.database.model.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    void deleteById(Long id);
}
