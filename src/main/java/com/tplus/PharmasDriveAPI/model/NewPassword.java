package com.tplus.PharmasDriveAPI.model;

public class NewPassword {

    private String code ;
    private String password ;
    private String email ;

    public NewPassword(String code , String password, String email){
        this.code = code ;
        this.password = password ;
        this.email = email ;
    }

    public String getCode(){
        return this.code ;
    }

    public void setCode (String code){
        this.code = code ;
    }

    public String getPassword(){
        return this.password ;
    }
    
    public void setPassword(String password){
        this.password = password ;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email ;
    }
    
}
