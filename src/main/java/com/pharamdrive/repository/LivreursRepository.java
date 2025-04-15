package com.pharamdrive.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pharamdrive.models.deliveryPerson;

public interface LivreursRepository extends MongoRepository<deliveryPerson,String>{
	@Query("{ 'id_pharmacie' : ?0 }")
	List<deliveryPerson> findAllById_pharmacie(String id_pharmacie);

}
