package com.example.freelance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "pharmacie")
public class Pharmacie implements Serializable {
    @Id
    String idPharmacie;
    String nom;
    String adresse;
    int telephone;
    List<Medicament> medicamentList;
    List<com.example.freelance.model.Document> documentList;
}
