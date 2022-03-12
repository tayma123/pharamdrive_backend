package com.tplus.PharmasDriveAPI.service;



import org.springframework.stereotype.Service;

@Service
public class StatusServiceImp implements StatusService{

    @Override
    public String  getStatus() {
        
        return "\n UNTREATED  \n INPROGRESS \n DELIVERED \n DELETED\n";
    }


}