package com.pharamdrive.repository;



import com.pharamdrive.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Users;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users,String>{

	Users findByEmailAndPassword(String email, String password);
	Optional<User> findUserByEmail(String email);
	Boolean existsByEmail(String email);

}
