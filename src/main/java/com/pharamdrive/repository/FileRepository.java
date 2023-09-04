package com.pharamdrive.repository;


import com.pharamdrive.models.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {
}
