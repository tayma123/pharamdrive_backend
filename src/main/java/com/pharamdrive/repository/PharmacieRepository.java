package com.pharamdrive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Pharmacie;

public interface PharmacieRepository extends MongoRepository<Pharmacie, String>{

}
