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
@Document(collection="pharmacy")
public class Pharmacy {


	@PersistenceConstructor
	public Pharmacy( String pharmacyName, String image, String address, String mobile,
			String telephone) {
				super();
				this.pharmacyName = pharmacyName;
				this.image = image ;
				this.address = address ;
				this.mobile = mobile ;
				this.telephone =telephone ;
	}

	@Id
	private ObjectId  _id ;

    @Field(value="id")
    private String id;


	@Field(value="pharmacyName")

	private String pharmacyName;
	@Field(value="image")

	private String image;
	@Field(value="address")

	private String address;
	@Field(value="mobile")

	private String mobile;
	@Field(value="telephone")

	private String telephone;



	public String getPharmacyName(){
		return pharmacyName ;
	}

	public void setPharmacyName(String pharmacyName){
		this.pharmacyName = pharmacyName ;
	}

	public String getObjectId(){
		return _id.toString();
	}



	public String getImage(){
		return image ;
	}

	public String getAddress(){
		return address ;
	}

	public String getTelephone(){
		return telephone ;
	}

	public String getMobile(){
		return mobile ;
	}

}
