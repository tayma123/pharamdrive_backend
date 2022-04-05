package com.pharamdrive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Admin;

public interface AdminRepository extends MongoRepository<Admin,String>{

	Admin findByEmailAndPassword(String email, String password);

}

