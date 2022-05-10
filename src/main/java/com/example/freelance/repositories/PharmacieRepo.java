package com.example.freelance.repositories;

import com.example.freelance.model.Pharmacie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PharmacieRepo extends MongoRepository<Pharmacie,String > {
}
