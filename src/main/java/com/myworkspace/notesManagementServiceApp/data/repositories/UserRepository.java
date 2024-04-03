package com.myworkspace.notesManagementServiceApp.data.repositories;

import com.myworkspace.notesManagementServiceApp.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
