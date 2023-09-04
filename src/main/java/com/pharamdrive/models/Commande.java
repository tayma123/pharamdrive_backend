package com.pharamdrive.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Commande {
    @Id
    String id;
    String idPharamcie;
    String idClient;
    Etat etat;
}
