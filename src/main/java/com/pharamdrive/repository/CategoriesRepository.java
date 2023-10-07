package com.pharamdrive.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pharamdrive.models.Categories;

public interface CategoriesRepository extends MongoRepository<Categories, String>{

	Categories findByCategoryName(String categoryName);

}
