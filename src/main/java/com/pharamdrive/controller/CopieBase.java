package com.pharamdrive.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class CopieBase {
    @Autowired

    private MongoClient sourceClient;
    @Autowired

    private MongoClient destinationClient;


    @PostMapping(value = "/copiebase/{base1}/{base2}")
    public String copiebase(@PathVariable(value = "base1") String base1,@PathVariable(value = "base2") String base2) {
        MongoDatabase sourcedb= sourceClient.getDatabase(base1);
        MongoDatabase destinationdb= destinationClient.getDatabase(base2);
        for(String collectionName:sourcedb.listCollectionNames()){
           MongoCollection<Document> sourcecollection= sourcedb.getCollection(collectionName);
            MongoCollection<Document> dscollection= destinationdb.getCollection(collectionName);
            //////
            for (Document documentFromBase1 : sourcecollection.find()) {
                Date lastModifiedFromBase1 = documentFromBase1.getDate("dateTimeModification");
                for(Document documentFromBase2:dscollection.find() ){
                    Date lastModifiedFromBase2 = documentFromBase2.getDate("dateTimeModification");
                if (lastModifiedFromBase1 != null && lastModifiedFromBase1!=lastModifiedFromBase2) {
                    dscollection.replaceOne(Filters.eq("_id", documentFromBase1.get("_id")), documentFromBase1);
                }
            }}
            ////////
//        List<Document> documents=sourcecollection.find().into(new ArrayList<>());
//        dscollection.insertMany(documents);dateTimeModification

        }
        return "true";
    }

}
