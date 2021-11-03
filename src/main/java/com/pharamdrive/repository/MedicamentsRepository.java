package com.pharamdrive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Medicaments;


public interface MedicamentsRepository  extends MongoRepository<Medicaments,String>{

}
