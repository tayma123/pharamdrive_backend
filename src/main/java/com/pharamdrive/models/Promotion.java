package com.pharamdrive.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "promotion")
public class Promotion {

    @Id
    String idPromotion;
    String promotion;
    LocalDateTime datedebut;
    LocalDateTime datefin;

}
