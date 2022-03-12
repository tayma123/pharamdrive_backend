package com.tplus.PharmasDriveAPI.model;


public class StatusBody {
    private String status ;
   

    public StatusBody(String status){
        this.status = status ;
     
    }

    public String getStatusBody(){
        return this.status ;
    }

    public void setStatusBody(String status){
        this.status = status ;
    }
    
}
