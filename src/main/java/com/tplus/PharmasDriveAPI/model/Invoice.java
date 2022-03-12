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
@Document(collection="invoice")
public class Invoice {


	@PersistenceConstructor
	public Invoice( String numInvoice, String amount,String letfToPay , String dateHour) {
				super();
				this.numInvoice = numInvoice;
				this.amount = amount ;
				this.letfToPay = letfToPay ;
				this.dateHour = dateHour ;
	}

	@Id
	private ObjectId  _id ;

    @Field(value="id")
    private String id;


	@Field(value="numInvoice")

	private String numInvoice;
	@Field(value="amount")

	private String amount;

	@Field(value="letfToPay")
	private String letfToPay ;
	@Field(value="dateHour")


	private String dateHour;




	public String getNunInvoice(){
		return numInvoice;
	}

	public void setNumInvoice(String numInvoice){
		this.numInvoice = numInvoice ;
	}

	public String getObjectId(){
		return _id.toString();
	}



	public String getAmount(){
		return amount ;
	}

    public  void setAmount(String amount){
        this.amount = amount ;
    }

	public String getLeftToPay(){
		return letfToPay ;
	}


	public void setLeftToPay(String leftToPay){
		this.letfToPay = leftToPay ;
	}

	public String getDateHour(){
		return dateHour ;
	}

    public void setDateHour(String dateHour){
        this.dateHour = dateHour ;
    }

}
