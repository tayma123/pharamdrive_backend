package com.example.freelance.services;

import com.example.freelance.model.*;
import com.example.freelance.repositories.DocumentRepo;
import com.example.freelance.repositories.PharmacieRepo;
import com.example.freelance.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepo documentRepo;
    private final PharmacieRepo pharmacieRepo;
    private final UserRepo userRepo;

    @Autowired
    public DocumentService(DocumentRepo documentRepo, PharmacieRepo pharmacieRepo, UserRepo userRepo) {
        this.documentRepo = documentRepo;
        this.pharmacieRepo = pharmacieRepo;
        this.userRepo = userRepo;
    }

    public Document getDocument(String idDocument) {
        return documentRepo.findById(idDocument).get();
    }

    public Document addDocument(String idUser, Document document) {
        document = documentRepo.save(document);
        User user = userRepo.findById(idUser).get();
        List<Document> documentList = new ArrayList<>();
        if (user.getMaPharmacie().getDocumentList() != null) {
            documentList.addAll(user.getMaPharmacie().getDocumentList());
        }
        documentList.add(document);
        Pharmacie pharmacie = user.getMaPharmacie();
        pharmacie.setDocumentList(documentList);
        pharmacieRepo.save(pharmacie);
        user.setMaPharmacie(pharmacie);
        userRepo.save(user);
        return document;
    }

    public Document updateDocument(String idUser, Document document) {
        document = documentRepo.save(document);
        User user = userRepo.findById(idUser).get();
        List<Document> documentList = new ArrayList<>();
        if (user.getMaPharmacie().getDocumentList() != null) {
            documentList.addAll(user.getMaPharmacie().getDocumentList());
        }
        for (int i = 0; i < documentList.size(); i++) {
            if (documentList.get(i).getIdDocument().equals(document.getIdDocument())) {
                documentList.set(i, document);
                Pharmacie pharmacie = user.getMaPharmacie();
                pharmacie.setDocumentList(documentList);
                pharmacieRepo.save(pharmacie);
                user.setMaPharmacie(pharmacie);
                userRepo.save(user);
            }
        }
        return document;
    }

    public void deleteDocument(String idUser, String idDocmuent) {
        User user = userRepo.findById(idUser).get();
        Document document = documentRepo.findById(idDocmuent).get();
        List<Document> documentList = new ArrayList<>();
        if (user.getMaPharmacie().getDocumentList() != null) {
            documentList.addAll(user.getMaPharmacie().getDocumentList());
        }
        for (int i = 0; i < documentList.size(); i++) {
            if (documentList.get(i).getIdDocument().equals(document.getIdDocument())) {
                documentList.remove(i);
                Pharmacie pharmacie = user.getMaPharmacie();
                pharmacie.setDocumentList(documentList);
                pharmacieRepo.save(pharmacie);
                user.setMaPharmacie(pharmacie);
                userRepo.save(user);
                documentRepo.deleteById(idDocmuent);
            }
        }

    }
    public Document addMedicamentToDocument(String idDocument, Medicament medicament){
        Document document=documentRepo.findById(idDocument).get();
        List<Medicament> medicaments=document.getMedicaments();
        medicaments.add(medicament);
        document.setMedicaments(medicaments);
        documentRepo.save(document);
        return document;
    }
    public Document addFournisseurToDocument(String idDocument, Fournisseur fournisseur){
        Document document=documentRepo.findById(idDocument).get();
        document.setFournisseur( fournisseur);

        documentRepo.save(document);
        return document;
    }
}
