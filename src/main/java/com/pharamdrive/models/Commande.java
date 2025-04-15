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
    State state;
    deliveryPerson deliveryPerson;
    String amountPay;
    LocalDateTime ConfirmationDate;
    LocalDateTime deliveryDate;
    List<Medicament> Purchasing;
    boolean active=true;
}
