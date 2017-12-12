package com.intellion.cms.service;

import com.intellion.cms.domain.PrescriptionEntry;

public interface PrescriptionEntryService {
	PrescriptionEntry save(PrescriptionEntry a);
	Iterable<PrescriptionEntry> findAll();
	PrescriptionEntry findOne(long id);
	void delete(long id);
}
