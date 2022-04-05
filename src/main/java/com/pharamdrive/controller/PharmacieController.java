package com.pharamdrive.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.models.Users;
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
	
	 @PostMapping("/login")
	    public Pharmacie login(@RequestBody Pharmacie pharmacie){

	        Pharmacie OldUser = pharmRepo.findByEmailAndPassword(pharmacie.getEmail(),pharmacie.getPassword());
	        return  OldUser;
	    }
	
	@GetMapping(value="/pharmacies")
	public List<Pharmacie> getPharmacies(){
		return pharmRepo.findAll();
	}
	
	@PostMapping(value ="/pharmacie/{id}")
	public Optional<Pharmacie> getPharmacieId(@PathVariable  String id ){
		return  pharmRepo.findById(id); 

	}
}
