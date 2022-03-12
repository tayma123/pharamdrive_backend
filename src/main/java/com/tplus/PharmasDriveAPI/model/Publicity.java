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
@Document(collection="publicity")
public class Publicity {


	@PersistenceConstructor
	public Publicity( String status, String codeNaf,String nTvaIntra) {
				super();
				this.status = status;
				this.codeNaf = codeNaf ;
				this.nTvaIntra = nTvaIntra ;
	}

	@Id
	private ObjectId  _id ;

    @Field(value="id")
    private String id;


	@Field(value="status")

	private String status;
	@Field(value="codeNaf")

	private String codeNaf;

	@Field(value="nTvaIntra")
	private String nTvaIntra ;
;



	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status ;
	}

	public String getObjectId(){
		return _id.toString();
	}



	public String getCodeNaf(){
		return codeNaf ;
	}

    public  void setCodeNaf(String codeNaf){
        this.codeNaf = codeNaf ;
    }

	public String getnTvaIntra(){
		return nTvaIntra ;
	}


	public void setnTvaIntra(String nTvaIntra){
		this.nTvaIntra = nTvaIntra ;
	}

}
