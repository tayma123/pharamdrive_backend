package com.pharamdrive.controller;

import com.pharamdrive.models.Commande;
import com.pharamdrive.models.Medicament;
import com.pharamdrive.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class CommandeController {
    private final CommandeRepository commandeRepository;
@Autowired
    public CommandeController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    //add Commande
    @PostMapping(value = "/addCommande")
    public String addCommande(@RequestBody Commande commande) {

        commandeRepository.save(commande);
        return "Commande added successfully";
    }
    //update Commande
    @PutMapping(value = "/updateCommande")
    public String updateCommande(@RequestBody Commande commande) {

        commandeRepository.save(commande);
        return "Commande updated successfully";
    }
    //get all commandes
    //get all commandes
    @GetMapping("/commandes/all")
    public List<Commande> getall() {
        return commandeRepository.findAll();
    }
    @GetMapping("/commandes/{id}")
    public List<Commande> getcommandes(@PathVariable(value = "id") String id) {
        return commandeRepository.getByIdPharamcie(id);
    }
}
