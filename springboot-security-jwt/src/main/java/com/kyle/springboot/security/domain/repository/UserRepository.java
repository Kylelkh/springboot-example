package com.kyle.springboot.security.domain.repository;


import com.kyle.springboot.security.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<com.kyle.springboot.security.domain.entity.User, String> {
  User findByUsername(String username);
}
