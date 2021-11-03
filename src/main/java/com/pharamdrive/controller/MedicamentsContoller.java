package com.pharamdrive.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharamdrive.models.Medicaments;
import com.pharamdrive.models.Users;
import com.pharamdrive.repository.MedicamentsRepository;
import com.pharamdrive.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")

public class MedicamentsContoller {
	@Autowired
	public MedicamentsRepository MedicamentsRepo;
	@PostMapping(value="/add/medicament")
	public Medicaments register(@RequestBody Medicaments medicament) {
		
		return MedicamentsRepo.save(medicament);
	}
	@GetMapping("/medicaments")
	public List<Medicaments> getAllEmployees() {
		return MedicamentsRepo.findAll();
	}
	//Api Delete medicament
	@DeleteMapping(value="/delete/medicament/{id}")
	public String deleteMedicament(@PathVariable(value = "id") String id  ) {
		MedicamentsRepo.deleteById(id);
		
		return "le medicament a été effacée avec succes";
	}
}
