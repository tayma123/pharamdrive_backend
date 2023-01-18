package com.pharamdrive.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pharamdrive.models.Categories;
import com.pharamdrive.models.Medicaments;
import com.pharamdrive.repository.CategoriesRepository;
import com.pharamdrive.repository.PharmacieRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class CategoriesController {
	@Autowired
	public CategoriesRepository catRepo;
	@Autowired
	public PharmacieRepository pharmRepo;
	
	@PostMapping(value="/add/categorie")
	
	public String  addNewCategorie(@RequestPart("file") MultipartFile file,@RequestParam String name) {
		//Random rand = new Random();
		Categories categorie = new Categories();
		String fileName = file.getOriginalFilename();
		
		try {
			file.transferTo(new File ("C:\\xampp\\htdocs\\storage\\"+fileName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		categorie.setFile(fileName);
		categorie.setCategory(name);
		catRepo.save(categorie);
		
		return "nouvelle categorie a ete ajoutee";
		
	}
	//get all categories
	@GetMapping("/categories")
	public List<Categories> getAllCategories(){
		return catRepo.findAll();
		}
	//get categories for a specific pharmacy
	@GetMapping("/categories/pharmacy/{id}")
	public List<Categories> getAllCategoriesPharmacys(@PathVariable String id) {
		 return catRepo.findAllById_pharmacie(id);
	
	}
	

	//delete an category
	@PostMapping("/delete/category/{id}")
	public String deleteCategory(@PathVariable(value="id") String id) {
		catRepo.deleteById(id);
		return "category deleted";
		
	}

}
