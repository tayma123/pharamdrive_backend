package com.pharamdrive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.repository.PharmacieRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class PharmacieController {
	@Autowired 
	public PharmacieRepository pharmRepo;
	@PostMapping(value="/new/pharmacie")
	public Pharmacie addPharmacie(@RequestBody Pharmacie pharam) {
		return pharmRepo.save(pharam);
	}
	
	@GetMapping(value="/pharmacies")
	public List<Pharmacie> getPharmacies(){
		return pharmRepo.findAll();
	}
}
