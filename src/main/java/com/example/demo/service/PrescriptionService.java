package com.example.demo.service;

import com.example.demo.domain.Prescription;

public interface PrescriptionService {
	Prescription save(Prescription a);
	Iterable<Prescription> findAll();
	Prescription findOne(long id);
	void delete(long id);
}
