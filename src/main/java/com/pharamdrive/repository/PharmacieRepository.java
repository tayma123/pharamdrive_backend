package com.pharamdrive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.models.Users;

public interface PharmacieRepository extends MongoRepository<Pharmacie, String>{

	Pharmacie findByEmailAndPassword(String email, String password);

}
