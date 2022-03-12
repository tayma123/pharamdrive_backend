package com.tplus.PharmasDriveAPI.model;

import java.util.UUID;

public class UserCode {

    public static String getCode(){
        return UUID.randomUUID().toString();
    }
    
}
