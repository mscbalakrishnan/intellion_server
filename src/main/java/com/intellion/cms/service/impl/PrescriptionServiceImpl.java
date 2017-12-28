package com.intellion.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Prescription;
import com.intellion.cms.domain.PrescriptionEntry;
import com.intellion.cms.repository.PrescriptionEntryRepository;
import com.intellion.cms.repository.PrescriptionRepository;
import com.intellion.cms.service.PrescriptionService;
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
	
	@Override
	public Iterable<Prescription> findByDoctor_Id(long id) {
		return prescriptionRepository.findByDoctor_Id(id);
	}
	
	@Override
	public Iterable<Prescription> findByPatient_Id(long id) {
		return prescriptionRepository.findByPatient_Id(id);
	}
	
	@Override
	public Iterable<Prescription> findByDoctor_IdAndPatient_Id(long doctorId, long patId) {
		return prescriptionRepository.findByDoctor_IdAndPatient_Id(doctorId, patId);
	}
	
	
	

}
