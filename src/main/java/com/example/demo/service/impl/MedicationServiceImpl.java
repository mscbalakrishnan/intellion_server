package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Medication;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.MedicationService;

@Component("medicationService")
public class MedicationServiceImpl implements MedicationService {
	private final MedicationRepository medicationRepository;
	private static final Logger logger = LoggerFactory.getLogger(MedicationServiceImpl.class);

	public MedicationServiceImpl(MedicationRepository medicationRepository) {
		super();
		this.medicationRepository = medicationRepository;
	}

	@Override
	public Medication save(Medication a) {
		return medicationRepository.save(a);
	}

	@Override
	public Iterable<Medication> findAll() {
		return medicationRepository.findAll();
	}

	@Override
	public Medication findOne(long id) {
		return medicationRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		medicationRepository.delete(id);
	}
	
	@Override
	public Iterable<Medication> findByMedicationName(String name) {
		return medicationRepository.findByNameContainingIgnoreCase(name);
	}

}
