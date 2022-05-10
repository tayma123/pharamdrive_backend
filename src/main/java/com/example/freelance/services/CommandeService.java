package com.example.freelance.services;

import com.example.freelance.model.Commande;
import com.example.freelance.model.User;
import com.example.freelance.repositories.CommandeRepo;
import com.example.freelance.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private final CommandeRepo commandeRepo;
    @Autowired
    private final UserRepo userRepo;

    public CommandeService(CommandeRepo commandeRepo, UserRepo userRepo) {
        this.commandeRepo = commandeRepo;
        this.userRepo = userRepo;
    }

    public List<Commande> updateCommand(Commande commande){
        List<Commande> commandes=new ArrayList<>();
        List<User> userList=userRepo.findAll();
      for (int i=0;i<userList.size();i++){
          List<Commande> commandeList=new ArrayList<>();
          if(userList.get(i).getMesCommandes()!=null){
              commandeList.addAll(userList.get(i).getMesCommandes());
          }
          for(int j=0;j<commandeList.size();j++){
              if(commandeList.get(j).getIdCommande().equals(commande.getIdCommande())){
                  commandeRepo.save(commande);
                  commandeList.set(j,commande);
                  userList.get(i).setMesCommandes(commandeList);
                  userRepo.save(userList.get(i));
                  commandes=commandeList;
                  break;
              }
          }
      }
      return commandes;
    }
    public List<Commande> deleteCommande(String idCommand){
        List<Commande> commandes=new ArrayList<>();

        List<User> userList=userRepo.findAll();
        for (int i=0;i<userList.size();i++){
            List<Commande> commandeList=new ArrayList<>();
            if(userList.get(i).getMesCommandes()!=null){
                commandeList.addAll(userList.get(i).getMesCommandes());
            }
            for(int j=0;j<commandeList.size();j++){
                if(commandeList.get(j).getIdCommande().equals(idCommand)){
                    commandeList.remove(j);
                    userList.get(i).setMesCommandes(commandeList);
                    userRepo.save(userList.get(i));
                    commandeRepo.deleteById(idCommand);
                    commandes=commandeList;
                    break;
                }
        }

    }
    return commandes;}
    public List<Commande> getAllCommands(){
        return commandeRepo.findAll();
    }
}
