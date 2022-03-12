package com.tplus.PharmasDriveAPI.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection="order")
public class Order {
@PersistenceConstructor
public Order( String nameUser, String drog,String amount , String dateHour){
			super();
			this.nameUser = nameUser;
			this.amount = amount ;
			this.drog = drog ;
			this.dateHour = dateHour ;
			this.status = Status.UNTREATED;
}
@Id
private ObjectId  _id ;

@Field(value="id")
private String id;
@Field(value="nameUser")
private String nameUser;
@Field(value="drog")
private String drog;
@Field(value="amount")
private String amount ;
@Field(value="dateHour")
private String dateHour;
@Field(value="status")
private Status status;
public String getNameUser(){
	return nameUser;
}
public void setNameUser(String nameUser){
	this.nameUser = nameUser ;
}
public String getObjectId(){
	return _id.toString();
}

public void setObjectId(ObjectId string){
	this._id = string ;
}
public String getDrog(){
	return drog ;
}
public  void setDrog(String drog){
    this.drog = drog ;
}
public String getAmount(){
	return amount ;
}
public void setAmount(String amount){
	this.amount = amount ;
}
public String getDateHour(){
	return dateHour ;
}
public void setDateHour(String dateHour){
    this.dateHour = dateHour ;
}

public Status getStatus (){
	return this.status ; 
}

public void setStatus (Status status){
	this.status = status ;
}
}
