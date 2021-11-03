package com.pharamdrive.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Users;

public interface UserRepository extends MongoRepository<Users,String>{

}
