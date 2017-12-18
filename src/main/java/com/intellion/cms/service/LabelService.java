package com.intellion.cms.service;

import com.intellion.cms.domain.Label;

public interface LabelService {
	
	Iterable<Label> findAll();

	Label findOne(long id);

	void delete(long id);
	
	Label save(Label label);
}
