package com.pharamdrive.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document(collection="livreur")
public class deliveryPerson {
	@Id
	private String id_livreur;
	private String name;
	private String email;
	private String phone;
	private String  car ;
	private  String idSocieteLivreur;
	private String address;
	private List<String> id_collabs;
	

}
