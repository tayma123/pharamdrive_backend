package com.example.freelance.api;

import com.example.freelance.model.Commande;
import com.example.freelance.model.Medicament;
import com.example.freelance.model.Pharmacie;
import com.example.freelance.repositories.MedicamentRepo;
import com.example.freelance.repositories.PharmacieRepo;
import com.example.freelance.repositories.UserRepo;
import com.example.freelance.services.MedicamentService;
import com.example.freelance.services.PharmacieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/medicament")
public class MedicamentController {
    private final PharmacieRepo pharmacieRepo;
    private final MedicamentService medicamentService;
    private final UserRepo userRepo;
@Autowired
    public MedicamentController(PharmacieService pharmacieService, PharmacieRepo pharmacieRepo, MedicamentService medicamentService, UserRepo userRepo) {
    this.pharmacieRepo = pharmacieRepo;
    this.medicamentService = medicamentService;


    this.userRepo = userRepo;
}
    @PostMapping("/addMedicamentToPharmacie/{idUser}")
    public ResponseEntity<Pharmacie> addMedicamentToPharmacie(@RequestBody Medicament medicament, @PathVariable("idUser") String idUser) {

        return ResponseEntity.status(HttpStatus.OK).body(medicamentService.addMedicamentToPharmacie(idUser,medicament));

    }
    @DeleteMapping("/deleteMedicament/{idUser}/{idMedicament}")
    public ResponseEntity<?> deleteMedicament(@PathVariable("idMedicament") String idMedicament,@PathVariable("idUser") String idUser) {
        medicamentService.deleteMedicamentFromPharmacie(idUser,idMedicament);
        return ResponseEntity.status(HttpStatus.OK).body("pharmacie supprimé avec succées");

    }
    @PostMapping("/updateMedicament/{idUser}")
    public ResponseEntity<Pharmacie> updateMedicament(@RequestBody Medicament medicament, @PathVariable("idUser") String idUser) {

        return ResponseEntity.status(HttpStatus.OK).body(medicamentService.updateMedicament(idUser,medicament));

    }
    @PostMapping("/addMedicamentToCommande/{idCommande}/{idMedicament}")
    public ResponseEntity<Commande> addMedicamentToCommande(@PathVariable("idCommande") String idCommande,@PathVariable("idMedicament") String idMedicament) {
        Commande commande= medicamentService.addMedicamentToCommande(idCommande,idMedicament);
        return ResponseEntity.status(HttpStatus.OK).body(commande);

    }
}
