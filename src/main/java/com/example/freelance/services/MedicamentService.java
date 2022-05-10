package com.example.freelance.services;

import com.example.freelance.model.Commande;
import com.example.freelance.model.Medicament;
import com.example.freelance.model.Pharmacie;
import com.example.freelance.model.User;
import com.example.freelance.repositories.CommandeRepo;
import com.example.freelance.repositories.MedicamentRepo;
import com.example.freelance.repositories.PharmacieRepo;
import com.example.freelance.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentService {
    private final MedicamentRepo medicamentRepo;
    private final UserRepo userRepo;
    private final PharmacieRepo pharmacieRepo;
    private final CommandeRepo commandeRepo;

    @Autowired
    public MedicamentService(MedicamentRepo medicamentRepo, UserRepo userRepo, PharmacieRepo pharmacieRepo, CommandeRepo commandeRepo) {
        this.medicamentRepo = medicamentRepo;
        this.userRepo = userRepo;
        this.pharmacieRepo = pharmacieRepo;
        this.commandeRepo = commandeRepo;
    }


    public Pharmacie addMedicamentToPharmacie(String idUser, Medicament medicament) {
        User user = userRepo.findById(idUser).get();
        List<Medicament> medicamentList = new ArrayList<>();
        if (user.getMaPharmacie().getMedicamentList() != null) {
            medicamentList.addAll(user.getMaPharmacie().getMedicamentList());
        }
        medicament = medicamentRepo.save(medicament);
        medicamentList.add(medicament);
        Pharmacie pharmacie = pharmacieRepo.findById(user.getMaPharmacie().getIdPharmacie()).get();
        pharmacie.setMedicamentList(medicamentList);
        pharmacieRepo.save(pharmacie);
        user.getMaPharmacie().setMedicamentList(medicamentList);
        userRepo.save(user);
        return user.getMaPharmacie();
    }
public Commande addMedicamentToCommande(String idCommande, String idMedicament){
        Medicament medicament=medicamentRepo.findById(idMedicament).get();
        Commande commande= commandeRepo.findById(idCommande).get();
    List<Medicament> medicamentList = new ArrayList<>();
    if (commande.getMedicaments() != null) {
        medicamentList.addAll(commande.getMedicaments());
    }
    medicamentList.add(medicament);
    commande.setMedicaments(medicamentList);
    commandeRepo.save(commande);
    return commande;
}
    public Pharmacie deleteMedicamentFromPharmacie(String idUser, String idMedicament) {
        User user = userRepo.findById(idUser).get();
        List<Medicament> medicamentList = new ArrayList<>();
        if (user.getMaPharmacie().getMedicamentList() != null) {
            medicamentList.addAll(user.getMaPharmacie().getMedicamentList());
        }
        for (int i = 0; i < medicamentList.size(); i++) {
            if (medicamentList.get(i).getIdMedicament().equals(idMedicament)) {
                medicamentList.remove(i);
                user.getMaPharmacie().setMedicamentList(medicamentList);
                userRepo.save(user);
                Pharmacie pharmacie = pharmacieRepo.findById(user.getMaPharmacie().getIdPharmacie()).get();
                pharmacie.setMedicamentList(medicamentList);
                pharmacieRepo.save(pharmacie);
                medicamentRepo.deleteById(idMedicament);
            }
        }
        return user.getMaPharmacie();
    }
    public Pharmacie updateMedicament(String idUser,Medicament medicament){
        medicament=medicamentRepo.save(medicament);
        User user = userRepo.findById(idUser).get();
        List<Medicament> medicamentList = new ArrayList<>();
        if (user.getMaPharmacie().getMedicamentList() != null) {
            medicamentList.addAll(user.getMaPharmacie().getMedicamentList());
        }
        for (int i = 0; i < medicamentList.size(); i++) {
            if (medicamentList.get(i).getIdMedicament().equals(medicament.getIdMedicament())) {
                medicamentList.set(i,medicament);
                user.getMaPharmacie().setMedicamentList(medicamentList);
                userRepo.save(user);
                Pharmacie pharmacie = pharmacieRepo.findById(user.getMaPharmacie().getIdPharmacie()).get();
                pharmacie.setMedicamentList(medicamentList);
                pharmacieRepo.save(pharmacie);

            }
        }
        return user.getMaPharmacie();
    }
}
