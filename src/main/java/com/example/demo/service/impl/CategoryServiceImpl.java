package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Component("categoryService")
//@Transactional

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
	
}
