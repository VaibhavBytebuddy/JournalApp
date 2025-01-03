package com.bytebuddy.JornalApp.repository;

import com.bytebuddy.JornalApp.entity.JournalEntry;
import com.bytebuddy.JornalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);

    void deleteByUserName(String name);
}
