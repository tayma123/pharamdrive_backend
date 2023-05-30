package com.pharamdrive.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
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
public class Pharmacie implements Serializable{
	@Id 
	private String id_pharmacie;
	private String name;
	private String representant;
	private String email;
	private String phone;
	private String adresse;
	private String password;
	private Date heures_ouverture;
	private Date heures_fermeture;
	private String rue;
	private Double altitude;
	private Double longitude;
	private String telephone;
	private Point location;
List<Notification> notifications;



	private boolean verified ;
	

	
	
	

}
