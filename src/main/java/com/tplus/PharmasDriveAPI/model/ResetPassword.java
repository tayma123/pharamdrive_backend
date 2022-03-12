package com.tplus.PharmasDriveAPI.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ResetPassword {


    private String email ;

    public  String getEmail(){
        return this.email ;
    }

    public  void setEmail(String email){
        this.email = email ;
    }
    
}
