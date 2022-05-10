package com.example.freelance.api;

import com.example.freelance.model.Commande;
import com.example.freelance.model.Document;
import com.example.freelance.model.Fournisseur;
import com.example.freelance.model.Medicament;
import com.example.freelance.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;
    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
    @PutMapping("/updateDocument/{idUser}")
        public ResponseEntity<Document> updateDocument(@RequestBody Document document,@PathVariable("idUser") String idUser) {

        return ResponseEntity.status(HttpStatus.OK).body(documentService.updateDocument(idUser,document));

    }
    @PostMapping("/addDocument/{idUser}")
    public ResponseEntity<Document> addDocument(@RequestBody Document document,@PathVariable("idUser") String idUser) {

        return ResponseEntity.status(HttpStatus.OK).body(documentService.addDocument(idUser,document));

    }
    @GetMapping("/getDocument/{idDocument}")
    public ResponseEntity<Document> getDocument(@PathVariable("idDocument") String idDocument){
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getDocument(idDocument));

    }
    @DeleteMapping("/deleteDocument/{idDocument}/{idUser}")
    public ResponseEntity<?> deleteDocument(@PathVariable("idDocument") String idDocument,@PathVariable("idUser") String idUser) {
        documentService.deleteDocument(idUser,idDocument);
        return ResponseEntity.status(HttpStatus.OK).body("le document est supprimé avec succée");
    }
    @PostMapping("/addMedicamentToDocument/{idUser}")
    public ResponseEntity<Document> addMedicamentToDocument(@RequestBody Medicament medicament, @PathVariable("idUser") String idUser) {

        return ResponseEntity.status(HttpStatus.OK).body(documentService.addMedicamentToDocument(idUser,medicament));

    }
    @PostMapping("/addFournisseurToDocument/{idUser}")
    public ResponseEntity<Document> addFournisseurToDocument(@RequestBody Fournisseur fournisseur, @PathVariable("idUser") String idUser) {

        return ResponseEntity.status(HttpStatus.OK).body(documentService.addFournisseurToDocument(idUser,fournisseur));

    }

    }
