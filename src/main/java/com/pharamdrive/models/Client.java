package com.pharamdrive.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Client {
    @Id
    String id;
    String name;
    Boolean withFamily;
    String civilStatus;
    String phoneNumber;
    String address;
    //matricule cnss ou mutuelle
    String registrationNumberCNSS;
    String registrationNumberMutual;
    //identification card cin
    String email;
    //famille detaillee

}
