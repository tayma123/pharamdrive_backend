
package com.tplus.PharmasDriveAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Mail {
    private String to ;
    private String code ;

    public Mail(String to , String code){
        this.to = to ;
        this.code = code ;
    }

    public String getTo(){
        return this.to ;
    }
    public void setTo(String to){
        this.to = to ;
    }

    public String getCode(){
        return this.code ;
    }

    public void setCode(String code){
        this.code = code ;
    }
    
}
