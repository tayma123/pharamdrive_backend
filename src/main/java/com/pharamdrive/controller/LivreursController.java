package com.pharamdrive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pharamdrive.models.deliveryPerson;
import com.pharamdrive.repository.LivreursRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class LivreursController {
	@Autowired LivreursRepository livreurRepo;
	
	//add new livreur
	@PostMapping(value="/add/livreur")
	public deliveryPerson addLivreur(@RequestBody deliveryPerson deliveryPerson) {
		return livreurRepo.save(deliveryPerson);

	}
	//update livreur
	@PutMapping(value="/update/livreur")
	public deliveryPerson updateLivreur(@RequestBody deliveryPerson deliveryPerson) {
		return livreurRepo.save(deliveryPerson);

	}
	//get medicaments for a specific pharmacy
	@GetMapping("/livreurs/pharmacy/{id}")
	public List<deliveryPerson> getAllLivreursPharmacys(@PathVariable String id) {
		 return livreurRepo.findAllById_pharmacie(id);
	}
	
	// get all livreurs
	@GetMapping(value="/livreurs/all")
	public List<deliveryPerson> getLivreurs(){
		return livreurRepo.findAll();
	}

}
