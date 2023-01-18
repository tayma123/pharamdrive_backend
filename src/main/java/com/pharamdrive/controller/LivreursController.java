package com.pharamdrive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharamdrive.models.Livreurs;
import com.pharamdrive.models.Medicaments;
import com.pharamdrive.repository.LivreursRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class LivreursController {
	@Autowired LivreursRepository livreurRepo;
	
	//add new livreur
	@PostMapping(value="/add/livreur")
	public Livreurs addLivreur(@RequestBody Livreurs livreur) {
		return livreurRepo.save(livreur);
		
	}
	//get medicaments for a specific pharmacy
	@GetMapping("/livreurs/pharmacy/{id}")
	public List<Livreurs> getAllLivreursPharmacys(@PathVariable String id) {
		 return livreurRepo.findAllById_pharmacie(id);
	}
	
	// get all livreurs
	@GetMapping(value="/livreurs")
	public List<Livreurs> getLivreurs(){
		return livreurRepo.findAll();
	}

}
