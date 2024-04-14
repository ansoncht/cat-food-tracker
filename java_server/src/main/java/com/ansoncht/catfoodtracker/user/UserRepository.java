package com.ansoncht.catfoodtracker.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDAO, String> {
    UserDAO findByEmail(String email);

    UserDAO findByUsername(String username);

    UserDAO updateUserDAOByEmail(String email);
}
