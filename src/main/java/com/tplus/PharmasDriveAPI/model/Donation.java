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
@Document(collection="donation")
public class Donation {


	@PersistenceConstructor
	public Donation( String client, String file,String amount , String receiver, String dateHour) {
				super();
				this.client = client;
				this.amount = amount ;
				this.file = file ;
				this.receiver = receiver ;
				this.dateHour = dateHour ;
	}

	@Id
	private ObjectId  _id ;

    @Field(value="id")
    private String id;


	@Field(value="client")

	private String client;
	@Field(value="file")

	private String file;

	@Field(value="amount")
	private String amount ;
	@Field(value="receiver")


	private String receiver;
	@Field(value="dateHour")

	private String dateHour;



	public String getClient(){
		return client;
	}

	public void setClient(String client){
		this.client = client ;
	}

	public String getObjectId(){
		return _id.toString();
	}



	public String getFile(){
		return file ;
	}

    public  void setFile(String file){
        this.file = file ;
    }

	public String getAmount(){
		return amount ;
	}


	public void setAmount(String amount){
		this.amount = amount ;
	}

	public String getReceiver(){
		return receiver ;
	}

    public void setReceiver(String receiver){
        this.receiver = receiver ;
    }

	public String getDateHour(){
		return dateHour ;
	}

    public void setDateHour(String dateHour){
        this.dateHour = dateHour ;
    }

}
