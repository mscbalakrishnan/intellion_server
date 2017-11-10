package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Prescription;
import com.example.demo.domain.PrescriptionEntry;
import com.example.demo.repository.PrescriptionEntryRepository;
import com.example.demo.repository.PrescriptionRepository;
import com.example.demo.service.PrescriptionService;
@Component("prescriptionService")
public class PrescriptionServiceImpl implements PrescriptionService {
	private final PrescriptionRepository prescriptionRepository;
	private final PrescriptionEntryRepository prescriptionEntryRepository;
	private static final Logger logger = LoggerFactory.getLogger(PrescriptionServiceImpl.class);
	
	public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,PrescriptionEntryRepository prescriptionEntryRepository) {
		super();
		this.prescriptionRepository = prescriptionRepository;
		this.prescriptionEntryRepository = prescriptionEntryRepository;
	}

	@Override
	public Prescription save(Prescription a) {
		Prescription prescription = prescriptionRepository.save(a);
		for (PrescriptionEntry p:a.getPrescriptionEntries()){
			p.setPrescription(prescription);
			prescriptionEntryRepository.save(p);
		}
		return prescription;
	}

	@Override
	public Iterable<Prescription> findAll() {
		return prescriptionRepository.findAll();
	}

	@Override
	public Prescription findOne(long id) {
		return prescriptionRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		Prescription prescription = prescriptionRepository.findOne(id);
		for (PrescriptionEntry pe : prescription.getPrescriptionEntries()) {
			prescriptionEntryRepository.delete(pe.getId());
		}
		prescriptionRepository.delete(id);
	}
}
