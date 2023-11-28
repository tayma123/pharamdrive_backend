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
public class Medicament implements Serializable{
	
	@Id
	private String id_medicament;
	private String nomMedicament;
	private String prix;
	private String remise;
	private String image="assets/icons/bio.png";
	private Integer quantite;
	private String detail;
	private String idPharmacie;
	private String  nomCategorie;
	private String idPromotion;
	private Integer seuil;
	private Integer AlerteForSeuil;
	private String tracabilite;


}
