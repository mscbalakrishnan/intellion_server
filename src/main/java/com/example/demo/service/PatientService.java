package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Patient;

public interface PatientService {
	Patient getPatient(String name);

	Patient save(Patient patient);

	Iterable<Patient> findAll();

	Patient findOne(String id);

	void delete(String id);

	void delete(Patient patient);

	List<Patient> findByName(String name);
}
