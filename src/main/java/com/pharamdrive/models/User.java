package com.pharamdrive.models;



import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@Document(collection = "user")
@CompoundIndexes({
        @CompoundIndex(name = "firstname_lastname", def = "{'firstname' : 1, 'lastname': 1}")
})
@EnableMongoAuditing
//Pour rendre les attributs prives, ajoutons l’annotation suivante ´
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User implements Serializable {


    @Id
//test git
    private String id;
    private String login;
    private String matricule;
    private String firstname;
    private String lastname;
    @Indexed
    private String username;
    private String direction;
    @Indexed
    private String email;
    private LocalDateTime creationDate;
    private LocalDateTime  modificationDate;

    private Boolean userWorkflow;

    private String functionUserWorkflow;
    private String affectCode;
    private String labelAffect;
    private Integer collaboratorId;

    private String idPicture;
    private String idPictMiniature;


    private Boolean enterPassword;
    private Boolean administrator;
    private String password ;
    private String passwordRecovery;
    private String userlang;

    private String lastCollabId;
    private String lastCollabFullName;
    private String lastCollabTitle;
    private String lastCollabGroupId;
    private String lastCollabGroupLabel;

    private String langue;
    private String theme;

    private Boolean confirmed;
    private String verificationId;
    private String resetId;
    @Value("${codeVersion}")
    Integer cv;

    Boolean showHelp;



}
