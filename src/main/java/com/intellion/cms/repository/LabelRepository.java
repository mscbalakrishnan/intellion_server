package com.intellion.cms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.Label;

@Repository("labelRepository")
public interface LabelRepository extends CrudRepository<Label, Long> {
	
	//Iterable<Label> findByCategory(String catName);
}
