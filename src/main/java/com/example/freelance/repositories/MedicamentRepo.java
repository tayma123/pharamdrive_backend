package com.example.freelance.repositories;

import com.example.freelance.model.Medicament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepo extends MongoRepository<Medicament,String> {
}
