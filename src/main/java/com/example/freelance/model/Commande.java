package com.example.freelance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "commande")
public class Commande {
    @Id
    String idCommande;
    String libellé;
    String quantité;
    Date date;
    List<Medicament> medicaments;
}
