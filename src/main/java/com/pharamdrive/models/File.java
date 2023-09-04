package com.pharamdrive.models;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
@Data
public class File {
    @Id
    String id;
    byte[] fileByte;
    Binary fileBinary;

}
