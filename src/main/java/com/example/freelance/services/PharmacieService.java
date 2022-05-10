package com.example.freelance.services;

import com.example.freelance.model.Pharmacie;
import com.example.freelance.model.User;
import com.example.freelance.repositories.PharmacieRepo;
import com.example.freelance.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacieService {
    @Autowired
    private final PharmacieRepo pharmacieRepo;
    @Autowired
    private final UserRepo userRepo;

    public PharmacieService(PharmacieRepo pharmacieRepo, UserRepo userRepo) {
        this.pharmacieRepo = pharmacieRepo;
        this.userRepo = userRepo;
    }
public Pharmacie getPharmacie(String idPharmacie){
        return pharmacieRepo.findById(idPharmacie).get();
}
    public Pharmacie updatePharmacie(Pharmacie pharmacie){

        List<User> users= userRepo.findAll();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getMaPharmacie().getIdPharmacie().equals(pharmacie.getIdPharmacie())){
                pharmacie= pharmacieRepo.save(pharmacie);
                users.get(i).setMaPharmacie(pharmacie);
                userRepo.save(users.get(i));
                break;


            }
        }

    return pharmacie;
}
public void deletePharmacie(String idPharmacie){
        Pharmacie pharmacie=pharmacieRepo.findById(idPharmacie).get();
    List<User> users= userRepo.findAll();
    for(int i=0;i<users.size();i++){
        if(users.get(i).getMaPharmacie().getIdPharmacie().equals(pharmacie.getIdPharmacie())){

            users.get(i).setMaPharmacie(null);
            userRepo.save(users.get(i));
            pharmacieRepo.deleteById(idPharmacie);
            break;


        }
    }
}
}
