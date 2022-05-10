package com.example.freelance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Fournisseur {
    @Id
    String idFournisseur;
    String nomFournisseur;
    int telephone;
}
