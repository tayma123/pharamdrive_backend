package com.pharamdrive.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Commande {
    @Id
    String id;
    String idPharamcie;
    Client client;
    Etat etat;
    Livreur livreur;
    String montantApaye;
    LocalDateTime dateDeConfirmation;
    LocalDateTime dateDeLivraison;
    List<Medicament> lesAchats;
    boolean active=true;
}
