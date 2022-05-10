package com.example.freelance.repositories;

import com.example.freelance.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends MongoRepository<Document,String> {
}
