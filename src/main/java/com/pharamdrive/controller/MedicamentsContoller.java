package com.pharamdrive.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pharamdrive.models.Categories;
import com.pharamdrive.models.Medicaments;
import com.pharamdrive.models.Users;
import com.pharamdrive.repository.MedicamentsRepository;
import com.pharamdrive.repository.PharmacieRepository;
import com.pharamdrive.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")

public class MedicamentsContoller {
	@Autowired
	public MedicamentsRepository MedicamentsRepo;
	@Autowired
	public PharmacieRepository pharmRepo;
	@PostMapping(value="/add/medicament")
	public String addMEdicaments(@RequestBody Medicaments med) {
		
		
		//Random rand = new Random();
		//Medicaments medic = new Medicaments();
		/*String fileName = file.getOriginalFilename();
		
		try {
			file.transferTo(new File ("C:\\xampp\\htdocs\\storage\\"+fileName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Medicaments medicament= MedicamentsRepo.save(med);
		
		
		return "medicaments added successfully";
	}
	//get medicaments for a specific pharmacy
	@GetMapping("/medicaments/pharmacy/{id}")
	public List<Medicaments> getAllMedicamentPharmacys(@PathVariable String id) {
	 return MedicamentsRepo.findAllById_pharmacie(id);
	}
	//get all medicaments 
	@GetMapping("/medicaments")
	public List<Medicaments> getAllMedicaments() {
		return MedicamentsRepo.findAll();
	}
	//Api get medicament
	@GetMapping(value="/medicament/{id}")
		public Optional<Medicaments> getMedicament(@PathVariable(value = "id") String id  ) {
			
			return MedicamentsRepo.findById(id);
		}
	//Api Delete medicament
	@DeleteMapping(value="/delete/medicament/{id}")
	public String deleteMedicament(@PathVariable(value = "id") String id  ) {
		MedicamentsRepo.deleteById(id);
		return "le medicament a été effacée avec succes";
	}
}
