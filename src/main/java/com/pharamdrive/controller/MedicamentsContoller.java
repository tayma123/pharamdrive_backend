package com.pharamdrive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharamdrive.models.Medicament;
import com.pharamdrive.repository.MedicamentsRepository;
import com.pharamdrive.repository.PharmacieRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")

public class MedicamentsContoller {
    @Autowired
    public MedicamentsRepository MedicamentsRepo;
    @Autowired
    public final PharmacieRepository pharmRepo;

    public MedicamentsContoller(PharmacieRepository pharmRepo) {
        this.pharmRepo = pharmRepo;
    }


    //add medicament under pharmacy
    @PostMapping(value = "/addMedicamentUnderPharmacy/{idPharmacy}")
    public String addmedicamentUnderPharmacy(@RequestBody Medicament med, @PathVariable String idPharmacy) {
        med.setIdPharmacie(idPharmacy);
        MedicamentsRepo.save(med);


        return "medicaments added successfully";
    }

    //get all medicaments for a specific pharmacy
    @GetMapping("/medicaments/pharmacy/{id}")
    public List<Medicament> getAllMedicamentPharmacys(@PathVariable String id) {
        return MedicamentsRepo.findAllByIdPharmacie(id);
    }

    //Api Delete medicament
    @DeleteMapping(value = "/delete/medicament/{id}")
    public String deleteMedicament(@PathVariable(value = "id") String id) {
        MedicamentsRepo.deleteById(id);
        return "le medicament a été effacée avec succes";
    }

    //Api get  medicament
    @GetMapping(value = "/medicament/{id}")
    public Optional<Medicament> getMedicament(@PathVariable(value = "id") String id) {

        return MedicamentsRepo.findById(id);
    }

    //get all medicaments
    @GetMapping("/medicaments")
    public List<Medicament> getAllMedicaments() {
        return MedicamentsRepo.findAll();
    }

    //add medicament
    @PostMapping(value = "/addMedicament")
    public String addMEdicaments(@RequestBody Medicament med) {

        MedicamentsRepo.save(med);
        return "medicaments added successfully";
    }

}
