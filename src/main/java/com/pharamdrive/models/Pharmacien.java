package com.pharamdrive.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Pharmacien {
    @Id
    String id;
    String nomPharmacien;
    String telephonNumber;
    String address;
    String idCollabs;
    String email;
    String pharmacyIdentifier;
}
