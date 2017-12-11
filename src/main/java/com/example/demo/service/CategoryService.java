package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Category;
import com.example.demo.domain.Doctor;

public interface CategoryService {
	
	Category save(Category cat);

	Iterable<Category> findAll();

	//Optional<Category> findOne(long id);

	//void delete(long id);

	//void delete(Category Category);

	
}
