package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Prescription;
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
//		for (PrescriptionEntry p:a.getPrescriptionEntries()){
//			prescriptionEntryRepository.save(p);
//		}
		return prescriptionRepository.save(a);
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
		prescriptionRepository.delete(id);
	}
}
