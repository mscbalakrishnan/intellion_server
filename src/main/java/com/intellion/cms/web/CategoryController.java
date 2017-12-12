package com.intellion.cms.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intellion.cms.domain.Category;
import com.intellion.cms.domain.dto.CategoryDto;
import com.intellion.cms.service.CategoryService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping(value="/intelhosp/categories")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping(value="/categorydto")
	@ResponseBody
	public List<CategoryDto> getAllCategories(HttpServletRequest request) {
		List<CategoryDto> toReturn = new ArrayList<>();
		List<Category> categories = (List<Category>) this.categoryService.findAll();
		categories.forEach(c->toReturn.add(new CategoryDto(c)));
		return toReturn;
	}
	
	@PostMapping
	@ResponseBody
	public CategoryDto addCategory(@RequestBody CategoryDto categoryDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , categoryDto.toString());
		Category category = new Category();
		category.setName(categoryDto.getName());
		category = this.categoryService.save(category);
		return new CategoryDto(category);
		
	}
	
	@DeleteMapping(value="/{categoryId}")
	@ResponseBody
	public void deleteCategory(@PathVariable("categoryId") Long categoryId ) {
		
		this.categoryService.delete(categoryId);
	
		
	}
	
	
	
	
	
	
}
