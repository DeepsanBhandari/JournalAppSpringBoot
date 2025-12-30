package com.deepsan.spring.repository;

import com.deepsan.spring.entity.JournalEntry;
import com.deepsan.spring.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
   User findByUserName(String userName);

   void deleteByUserName(String userName);
}
