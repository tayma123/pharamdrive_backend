package com.pharamdrive.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pharamdrive.models.Medicaments;


public interface MedicamentsRepository  extends MongoRepository<Medicaments,String>{
	@Query("{ 'id_pharmacie' : ?0 }")
	List<Medicaments> findAllById_pharmacie(String id_pharmacie);

	

}
