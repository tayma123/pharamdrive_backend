package com.example.freelance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "medicament")
public class Medicament {
    @Id
    String idMedicament;
    String nomMedicament;
    String prix;
    String remise;
    Categorie categorie;
}
