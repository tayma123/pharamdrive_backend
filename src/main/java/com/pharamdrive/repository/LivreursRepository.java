package com.pharamdrive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Livreurs;

public interface LivreursRepository extends MongoRepository<Livreurs,String>{

}
