package com.pharamdrive.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Collaborator {
    @Id
    String id;
    String address;
    String age;
    String email;
}
