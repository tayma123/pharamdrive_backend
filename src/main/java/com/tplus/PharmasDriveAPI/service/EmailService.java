package com.tplus.PharmasDriveAPI.service;

import com.tplus.PharmasDriveAPI.model.Mail;

public interface EmailService {

    public void sendCodeByEmail(Mail email);
    
}
