package com.intellion.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Category;
import com.intellion.cms.repository.CategoryRepository;
import com.intellion.cms.service.CategoryService;

@Component("categoryService")
public class CategoryServiceImpl implements CategoryService{
	private final CategoryRepository categoryRepository;
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public Category save(Category cat) {
		Category cat1 = categoryRepository.save(cat);
		logger.debug("Saved Object is {}",cat1);
		return cat1;
	}
	@Override
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void delete(long id) {
		categoryRepository.deleteById(id);
		
	}
	
	@Override
	public Category findOne(long id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
	
	
}
