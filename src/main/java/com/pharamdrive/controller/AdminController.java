package com.pharamdrive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharamdrive.models.Admin;
import com.pharamdrive.repository.AdminRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class AdminController {
	// add and login as super admin
	@Autowired 
	public AdminRepository adminRepo;
	@PostMapping(value="/new/admin")
	public Admin addAdmin(@RequestBody Admin newadmin) {
		return adminRepo.save(newadmin);
	}
	
	 @PostMapping("/login/admin")
	    public Admin login(@RequestBody Admin admin){

	        Admin OldUser = adminRepo.findByEmailAndPassword(admin.getEmail(),admin.getPassword());
	        return  OldUser;
	    }
	
	
}
