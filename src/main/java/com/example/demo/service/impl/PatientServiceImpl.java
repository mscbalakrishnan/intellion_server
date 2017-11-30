package com.example.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;

@Component("patientService")
//@Transactional
public class PatientServiceImpl implements PatientService {
	
	private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);	
	private final PatientRepository patientRepository;
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	@Override
	public Patient getPatient(String name) {
		return null;
	}

	@Override
	public Patient save(Patient patient) {
		/*List<Patient> list = findByName(patient.getName());
		if (list.size() > 0) {
			//Alreaady Exists
			throw new IllegalArgumentException("Patient already present");
		}*/
		Patient p = patientRepository.save(patient);
		logger.debug("Saved Object is {}",p);
		return p;
	}

	@Override
	public Iterable<Patient> findAll() {
		return patientRepository.findAll();
	}

	@Override
	public Patient findOne(long id) {
		return patientRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		patientRepository.delete(id);
	}

	@Override
	public void delete(Patient patient) {
		patientRepository.delete(patient);
	}

	@Override
	public List<Patient> findByName(String name) {
		return patientRepository.findByNameContainingIgnoreCase(name);
	}

}
