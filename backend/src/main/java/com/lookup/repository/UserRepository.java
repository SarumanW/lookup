package com.lookup.repository;

import com.lookup.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    User findBy_id(ObjectId _id);

    User findByName(String name);
}
