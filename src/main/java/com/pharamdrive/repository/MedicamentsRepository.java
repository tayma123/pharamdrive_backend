package com.pharamdrive.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pharamdrive.models.Medicament;


public interface MedicamentsRepository  extends MongoRepository<Medicament,String>{
	@Query("{ 'id_pharmacie' : ?0 }")
	List<Medicament> findAllByIdPharmacie(String idPharmacie);
	Optional<Medicament> findByIdPharmacieAndNomMedicament(String idPharmacie, String nomMedicament);

	

}
