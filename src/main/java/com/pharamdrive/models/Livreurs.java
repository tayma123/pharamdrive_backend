package com.pharamdrive.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document(collection="livreurs")
public class Livreurs {
	@Id
	private String id_livreur;
	private String name;
	private String email;
	private String phone;
	private String  car ;
	private String addresse;
	private String id_pharmacie;
	

}
