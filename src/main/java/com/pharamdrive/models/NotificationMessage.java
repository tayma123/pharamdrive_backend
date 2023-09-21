package com.pharamdrive.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "notificationMessage")
@EnableMongoAuditing
//Pour rendre les attributs prives, ajoutons l’annotation suivante ´
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationMessage implements Serializable {
    @Id
    String id;
    private String senderName;
    private String recipientName;
    private String recipientEmail;
    //dans le subject de notre contenu de message
    private String subject;
    private String[][] contextVariables;

    private String[][] attachementsPaths;
    private String username;
    private String htmltemplate;
    private String formattedThymeleafMessage;
    private String simpleMimeMessage;
    private Boolean send;
    private int sendTentaive;
    private String adresseMac;
    private String adresseIp;
    private String hostName;
    public String getAdresseIp() {
        return adresseIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setAdresseIp(String adresseIp) {
        this.adresseIp = adresseIp;
    }

    public String getAdresseMac() {
        return adresseMac;
    }

    public void setAdresseMac(String adresseMac) {
        this.adresseMac = adresseMac;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public int getSendTentaive() {
        return sendTentaive;
    }

    public void setSendTentaive(int sendTentaive) {
        this.sendTentaive = sendTentaive;
    }

    public NotificationMessage() {
        super();
    }


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[][] getContextVariables() {
        return contextVariables;
    }

    public void setContextVariables(String[][] contextVariables) {
        this.contextVariables = contextVariables;
    }




    public String[][] getAttachementsPaths() {
        return attachementsPaths;
    }

    public void setAttachementsPaths(String[][] attachementsPaths) {
        this.attachementsPaths = attachementsPaths;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHtmltemplate() {
        return htmltemplate;
    }

    public void setHtmltemplate(String htmltemplate) {
        this.htmltemplate = htmltemplate;
    }

    public String getFormattedThymeleafMessage() {
        return formattedThymeleafMessage;
    }

    public void setFormattedThymeleafMessage(String formattedThymeleafMessage) {
        this.formattedThymeleafMessage = formattedThymeleafMessage;
    }

    public String getSimpleMimeMessage() {
        return simpleMimeMessage;
    }

    public void setSimpleMimeMessage(String simpleMimeMessage) {
        this.simpleMimeMessage = simpleMimeMessage;
    }


}
