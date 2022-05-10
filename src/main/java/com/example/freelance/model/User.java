package com.example.freelance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "user")
public class User  implements Serializable {
    @Id
    String id;
    String nom;
    String prenom;
    Pharmacie MaPharmacie;
    List<Commande> mesCommandes;
}
