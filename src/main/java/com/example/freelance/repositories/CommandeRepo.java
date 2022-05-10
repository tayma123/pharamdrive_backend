package com.example.freelance.repositories;

import com.example.freelance.model.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CommandeRepo extends MongoRepository<Commande,String> {
}
