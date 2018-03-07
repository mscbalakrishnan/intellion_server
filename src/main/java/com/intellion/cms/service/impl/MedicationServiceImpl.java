package com.intellion.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Medication;
import com.intellion.cms.repository.MedicationRepository;
import com.intellion.cms.service.MedicationService;

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
		return medicationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public void delete(long id) {
		medicationRepository.deleteById(id);
	}
	
	@Override
	public Iterable<Medication> findByMedicationName(String name) {
		return medicationRepository.findByNameContainingIgnoreCase(name);
	}

}
