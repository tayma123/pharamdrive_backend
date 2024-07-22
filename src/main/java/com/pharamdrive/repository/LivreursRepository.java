package com.pharamdrive.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pharamdrive.models.Livreur;

public interface LivreursRepository extends MongoRepository<Livreur,String>{
	@Query("{ 'id_pharmacie' : ?0 }")
	List<Livreur> findAllById_pharmacie(String id_pharmacie);

}
