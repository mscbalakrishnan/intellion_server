package com.intellion.cms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.Category;

@Repository()
public interface CategoryRepository extends CrudRepository<Category, Long>{
//	Page<Doctor> findAll(Pageable pageable);
}
