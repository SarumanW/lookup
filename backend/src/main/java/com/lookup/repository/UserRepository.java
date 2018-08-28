package com.lookup.repository;

import com.lookup.domain.Uuser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Uuser, String> {
    Uuser findBy_id(ObjectId _id);

    Uuser findByName(String name);
}
