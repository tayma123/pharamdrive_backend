package com.pharamdrive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pharamdrive.models.Categories;

public interface CategoriesRepository extends MongoRepository<Categories, String>{

}
