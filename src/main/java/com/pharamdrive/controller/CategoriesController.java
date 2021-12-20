package com.pharamdrive.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pharamdrive.models.Categories;
import com.pharamdrive.repository.CategoriesRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class CategoriesController {
	@Autowired
	public CategoriesRepository catRepo;
	@PostMapping(value="/add/categorie")
	
	public String  addNewCategorie(@RequestPart("file") MultipartFile file,@RequestParam String name) {
		Random rand = new Random();
		Categories categorie = new Categories();
		String fileName = rand+file.getOriginalFilename();
		
		try {
			file.transferTo(new File ("F:\\storage\\"+fileName));
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

}
