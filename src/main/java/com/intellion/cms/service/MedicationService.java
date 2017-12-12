package com.intellion.cms.service;

import com.intellion.cms.domain.Medication;

public interface MedicationService {
	Medication save(Medication a);
	Iterable<Medication> findAll();
	Iterable<Medication> findByMedicationName(String name);
	Medication findOne(long id);
	void delete(long id);
}
