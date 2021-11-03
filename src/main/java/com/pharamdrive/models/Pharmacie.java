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


@Document(collection="pharmacie")
public class Pharmacie {
	@Id 
	private String id_pharmacie;
	private String name;
	private String representant;
	private String email;
	private String phone;
	private String password;
	

}
