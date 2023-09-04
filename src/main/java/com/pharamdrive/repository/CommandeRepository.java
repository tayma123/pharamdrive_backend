package com.pharamdrive.repository;

import com.pharamdrive.models.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommandeRepository extends MongoRepository<Commande,String> {
    List<Commande> getByIdPharamcie(String idPharamcie);
}
