package com.intellion.cms.service;

import com.intellion.cms.domain.Category;

public interface CategoryService {
	
	Category save(Category cat);

	Iterable<Category> findAll();

	//Optional<Category> findOne(long id);

	//void delete(long id);

	//void delete(Category Category);

	
}
