package com.pharamdrive.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.models.Users;

public interface PharmacieRepository extends MongoRepository<Pharmacie, String>{

	Pharmacie findByEmailAndPassword(String email, String password);
	List<Pharmacie> findByDepartementContainingIgnoreCase(String quartier);
	List<Pharmacie> findByZipCode(String zipCode);
	List<Pharmacie> findByAdresseContainingIgnoreCase(String adresse);


	List<Pharmacie> findByZipCodeContaining(String query);

	List<Pharmacie> findByVilleNameContainingIgnoreCase(String query);
}
