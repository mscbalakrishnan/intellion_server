package com.intellion.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.PrescriptionEntry;
import com.intellion.cms.repository.PrescriptionEntryRepository;
import com.intellion.cms.repository.PrescriptionRepository;
import com.intellion.cms.service.PrescriptionEntryService;
@Component("prescriptionEntryService")
public class PrescriptionEntryServiceImpl implements PrescriptionEntryService {
	private final PrescriptionEntryRepository prescriptionEntryRepository;
	private static final Logger logger = LoggerFactory.getLogger(PrescriptionEntryServiceImpl.class);
	
	public PrescriptionEntryServiceImpl(PrescriptionRepository prescriptionRepository,PrescriptionEntryRepository prescriptionEntryRepository) {
		super();
		this.prescriptionEntryRepository = prescriptionEntryRepository;
	}

	@Override
	public PrescriptionEntry save(PrescriptionEntry a) {
		return prescriptionEntryRepository.save(a);
	}

	@Override
	public Iterable<PrescriptionEntry> findAll() {
		return prescriptionEntryRepository.findAll();
	}

	@Override
	public PrescriptionEntry findOne(long id) {
		return prescriptionEntryRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		prescriptionEntryRepository.delete(id);
	}
}
