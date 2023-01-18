package com.pharamdrive.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor


@Document(collection="medicaments")
public class Medicaments implements Serializable{
	
	@Id
	private String id_medicament;
	private String medicament;
	private String prix;
	private String remise;
	private String image="assets/icons/bio.png";
	private String quantite;
	private String id_pharmacie;
	private String  id_categorie;

}
