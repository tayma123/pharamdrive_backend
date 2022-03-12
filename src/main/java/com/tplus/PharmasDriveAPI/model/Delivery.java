package com.tplus.PharmasDriveAPI.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
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
@Document(collection="delivery")
public class Delivery {


	@PersistenceConstructor
	public Delivery( String name, String email,String phone , String car, String address) {
				super();
				this.name = name;
				this.email = email ;
				this.phone = phone ;
				this.car = car ;
				this.address = address ;
	}

	@Id
	private ObjectId  _id ;

    @Field(value="id")
    private String id;


	@Field(value="name")

	private String name;
	@Field(value="email")

	private String email;

	@Field(value="phone")
	private String phone ;
	@Field(value="car")


	private String car;
	@Field(value="address")

	private String address;



	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name ;
	}

	public String getObjectId(){
		return _id.toString();
	}



	public String getEmail(){
		return email ;
	}

    public  void setEmail(String email){
        this.email = email ;
    }

	public String getPhone(){
		return phone ;
	}


	public void setPhone(String phone){
		this.phone = phone ;
	}

	public String getCar(){
		return car ;
	}

    public void setCar(String car){
        this.car = car ;
    }

	public String getAddress(){
		return address ;
	}

    public void setAddress(String address){
        this.address = address ;
    }

}
