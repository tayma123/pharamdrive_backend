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
import com.pharamdrive.models.Users;
import com.pharamdrive.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")

public class UserController {
	@Autowired
	public UserRepository userRep;
	
	@PostMapping(value="/user")
	public Users register(@RequestBody Users user) {
		
		return userRep.save(user);
	}
	
	//api to login in pharmadrive mobile
	@PostMapping("/loginuser")
    public Users loginuser(@RequestBody Users user){

        Users OldUser = userRep.findByEmailAndPassword(user.getEmail(),user.getPassword());
        return  OldUser;
    }
	
	
	
	@GetMapping("/users")
	public List<Users> getAllEmployees() {
		return userRep.findAll();
	}
}
