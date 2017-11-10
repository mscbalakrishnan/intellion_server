package com.example.demo.service;

import com.example.demo.domain.PrescriptionEntry;

public interface PrescriptionEntryService {
	PrescriptionEntry save(PrescriptionEntry a);
	Iterable<PrescriptionEntry> findAll();
	PrescriptionEntry findOne(long id);
	void delete(long id);
}
