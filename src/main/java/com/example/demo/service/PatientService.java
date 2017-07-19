package com.example.demo.service;

import com.example.demo.domain.Patient;

public interface PatientService {
	Patient getPatient(String name);

	Patient save(Patient patient);

	Iterable<Patient> findAll();

	Patient findOne(long id);

	void delete(long id);

	void delete(Patient patient);
}
