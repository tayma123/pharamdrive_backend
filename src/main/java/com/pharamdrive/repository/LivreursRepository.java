package com.pharamdrive.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pharamdrive.models.Livreurs;

public interface LivreursRepository extends MongoRepository<Livreurs,String>{
	@Query("{ 'id_pharmacie' : ?0 }")
	List<Livreurs> findAllById_pharmacie(String id_pharmacie);

}
