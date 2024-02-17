package com.pharamdrive.controller;

import com.pharamdrive.models.Client;
import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.repository.ClientRepository;
import com.pharamdrive.repository.PharmacieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PharmacieRepository pharmacieRepository;

    @PostMapping(value = "/addClient/{id}")
    public Client save(@RequestBody Client client,@PathVariable String id) {
        client= clientRepository.save(client);
        Pharmacie pharmacie=pharmacieRepository.findById(id).get();
        List<String> clientsIds = new ArrayList<>();
        if (pharmacie.getIdClients() != null) {
            clientsIds.addAll(pharmacie.getIdClients());
        }
        clientsIds.add(client.getId());
        pharmacie.setIdClients(clientsIds);
        pharmacieRepository.save(pharmacie);
        return client;
    }

    @GetMapping(value = "/pharmacieClients/{id}")
    public List<Client> getClientOfPharmacy(@PathVariable String id) {
        Pharmacie pharmacie = pharmacieRepository.findById(id).get();
        List<String> clientsIds = new ArrayList<>();
        if (pharmacie.getIdClients() != null) {
            clientsIds.addAll(pharmacie.getIdClients());
        }
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < clientsIds.size(); i++) {
            clients.add(clientRepository.findById(clientsIds.get(i)).get());


        }

        return clients;
    }

    @GetMapping(value = "/clients/all")
    public List<Client> givemeall() {
        List<Client> clients=clientRepository.findAll();

        return clients;
    }

}
