package com.example.demo.service;

import com.example.demo.domain.Medication;

public interface MedicationService {
	Medication save(Medication a);
	Iterable<Medication> findAll();
	Medication findOne(long id);
	void delete(long id);
}
