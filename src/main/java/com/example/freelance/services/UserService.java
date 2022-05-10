package com.example.freelance.services;

import com.example.freelance.model.Commande;
import com.example.freelance.model.Pharmacie;
import com.example.freelance.model.User;
import com.example.freelance.repositories.CommandeRepo;
import com.example.freelance.repositories.PharmacieRepo;
import com.example.freelance.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
@Autowired
    private  UserRepo userRepo;
@Autowired
    private  PharmacieRepo pharmacieRepo;
@Autowired
    private CommandeRepo commandeRepo;


    public UserService(UserRepo userRepo, PharmacieRepo pharmacieRepo, CommandeRepo commandeRepo) {
        this.userRepo = userRepo;
        this.pharmacieRepo = pharmacieRepo;
        this.commandeRepo = commandeRepo;
    }
public List<User> getUsers(){
        return userRepo.findAll();
}

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(String idUser) {
        userRepo.deleteById(idUser);
    }

    public User getUser(String idUser) {
        return userRepo.findById(idUser).get();
    }

    public User addPharmacieToUser(String idUser, Pharmacie pharmacie) {
        pharmacie= pharmacieRepo.save(pharmacie);
        User user = userRepo.findById(idUser).get();
        user.setMaPharmacie(pharmacie);
        userRepo.save(user);
        return user;
    }

    public User addCommandToUser(String idUser, Commande commande) {
        User user = userRepo.findById(idUser).get();
        commande= commandeRepo.save(commande);
        List<Commande> commandes = new ArrayList<>();
        if (user.getMesCommandes() != null) {
            commandes = user.getMesCommandes();
        }
        commandes.add(commande);
        user.setMesCommandes(commandes);
        userRepo.save(user);
        return user;
    }
}
