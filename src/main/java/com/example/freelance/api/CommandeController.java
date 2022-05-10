package com.example.freelance.api;

import com.example.freelance.model.Commande;
import com.example.freelance.repositories.CommandeRepo;
import com.example.freelance.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;
    private final CommandeRepo commandeRepo;
    @Autowired
    public CommandeController(CommandeService commandeService, CommandeRepo commandeRepo) {
        this.commandeService = commandeService;
        this.commandeRepo = commandeRepo;
    }
    @PutMapping("/updateCommande")
    public ResponseEntity<List<Commande>> updateCommande(@RequestBody Commande commande) {
        List<Commande> commandes= commandeService.updateCommand(commande);
        return ResponseEntity.status(HttpStatus.OK).body(commandes);

    }
    @DeleteMapping("/deleteCommande/{idCommande}")
    public ResponseEntity<?> deletePharmacie(@PathVariable("idCommande") String idCommande) {
        commandeService.deleteCommande(idCommande);
        return ResponseEntity.status(HttpStatus.OK).body("la commande  etait supprimé avec succées");

    }
    @GetMapping("/{idCommande}")
    public Commande getCommande(@PathVariable("idCommande") String idCommande){
        return commandeRepo.findById(idCommande).get();
    }

}
