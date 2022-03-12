package com.tplus.PharmasDriveAPI.model;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class User {

	@Id
	private ObjectId  _id ;

    @Field(value="id")
    private String id;
	@Field(value="name")
	private String name;
	@Field(value="age")
	private Long age = 0L ;
	@Field(value="location")
    private String location;
	@Field(value="email")
	private String email;
	@Field(value="password")
	private String password;
	@Field(value="code")
	private String code;
	/*@CreationTimestamp
	@Column(name ="created_at" , nullable = false , updatable = false)
	private Date createdAt ;
	@UpdateTimestamp
	@Column(name ="updated_at")
	private Date updatedAt ;*/

	public String getName(){
		return name ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public Long getAge(){
		return age ;
	}
	public void setAge(Long age){
		this.age = age ;
	}

	public void setLocation(String location){
		this.location = location ;
	}

	public String getLocation(){
		return location ;
	}

	public void setPassword(String password){
		this.password = password ;
	}
	public void setEmail(String email){
		this.email = email ;
	}
	public String getEmail() {
		return this.email ;
	}
	public String getPassword() {

		return this.password ;
	}
	public String getCode(){
		return this.code ;
	}
	public void setCode(String code){
		this.code = code ;
	}

}
