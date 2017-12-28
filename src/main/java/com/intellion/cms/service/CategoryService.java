package com.intellion.cms.service;

import com.intellion.cms.domain.Category;
import com.intellion.cms.domain.Medication;

public interface CategoryService {
	
	Category save(Category cat);

	Iterable<Category> findAll();

	void delete(long id);

	Category findOne(long id);
	
}
