package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String emailId);
    User findByUserId(String id);
    User deleteByUserId(String id);
}
