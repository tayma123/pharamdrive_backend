package com.example.freelance.api;

import com.example.freelance.model.Pharmacie;
import com.example.freelance.model.User;
import com.example.freelance.services.PharmacieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pharmacie")
public class PharmacieController {

    private final PharmacieService pharmacieService;
    @Autowired
    public PharmacieController(PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }
    @PutMapping("/updatePharmacie")
    public ResponseEntity<Pharmacie> updatePharmacie(@RequestBody Pharmacie pharmacie) {
        pharmacie= pharmacieService.updatePharmacie(pharmacie);
        return ResponseEntity.status(HttpStatus.OK).body(pharmacie);

    }
    @GetMapping("/getPharmacie/{idPharmacie}")
    public ResponseEntity<Pharmacie> getPharmacie(@PathVariable("id") String idPharmacie) {

        return ResponseEntity.status(HttpStatus.OK).body(pharmacieService.getPharmacie(idPharmacie));

    }
    @DeleteMapping("/deletePharmacie/{idPharmacie}")
    public ResponseEntity<?> deletePharmacie(@PathVariable("id") String idPharmacie) {
        pharmacieService.deletePharmacie(idPharmacie);
        return ResponseEntity.status(HttpStatus.OK).body("pharmacie supprimé avec succées");

    }
}
