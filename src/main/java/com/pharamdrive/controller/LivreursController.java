package com.pharamdrive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pharamdrive.models.Livreur;
import com.pharamdrive.repository.LivreursRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class LivreursController {
	@Autowired LivreursRepository livreurRepo;
	
	//add new livreur
	@PostMapping(value="/add/livreur")
	public Livreur addLivreur(@RequestBody Livreur livreur) {
		return livreurRepo.save(livreur);

	}
	//update livreur
	@PutMapping(value="/update/livreur")
	public Livreur updateLivreur(@RequestBody Livreur livreur) {
		return livreurRepo.save(livreur);

	}
	//get medicaments for a specific pharmacy
	@GetMapping("/livreurs/pharmacy/{id}")
	public List<Livreur> getAllLivreursPharmacys(@PathVariable String id) {
		 return livreurRepo.findAllById_pharmacie(id);
	}
	
	// get all livreurs
	@GetMapping(value="/livreurs/all")
	public List<Livreur> getLivreurs(){
		return livreurRepo.findAll();
	}

}
