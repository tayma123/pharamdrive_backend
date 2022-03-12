package com.tplus.PharmasDriveAPI.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountResponse {

    private int result;

    public int getResult(){
        return result ;
    }

    public void setResult(int result){
        this.result = result;
    }
    
}
