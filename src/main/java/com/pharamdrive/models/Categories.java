package com.pharamdrive.models;

import java.io.Serializable;
import java.util.List;

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

@Document(collection="categories")
public class Categories implements Serializable {
	@Id
	private String id;
	private String categoryName;
	private String file;
	private String id_pharmacie;
	
	
	

}
