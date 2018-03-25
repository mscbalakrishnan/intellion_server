package com.intellion.cms.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Patient;
import com.intellion.cms.repository.PatientRepository;
import com.intellion.cms.service.PatientService;

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
		Patient p = null;
		try {
			p = patientRepository.save(patient);
		} catch (Exception e1) {
			Exception e = (Exception)e1.getCause();
			e = (Exception)e.getCause();
			if (e instanceof ConstraintViolationException){
				ConstraintViolationException e2 = (ConstraintViolationException) e;
				String errMsg = "";
				for (ConstraintViolation cv : e2.getConstraintViolations()){
					errMsg += cv.getMessageTemplate();
				}
				throw new IllegalArgumentException(errMsg);
			} else {
				throw e1;
			}
			
		}
		logger.debug("Saved Object is {}",p);
		return p;
	}

	@Override
	public Iterable<Patient> findAll() {
		return patientRepository.findAllByEnabledTrueOrderByLastModifiedTimeDesc();
	}

	@Override
	public Patient findOne(String id) {
		return patientRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public void delete(String id) {
//		patientRepository.deleteById(id);
		patientRepository.disablePatient(id, false);
	}

	@Override
	public void delete(Patient patient) {
//		patientRepository.delete(patient);
		patientRepository.disablePatient(patient.getId(), false);
	}

	@Override
	public List<Patient> findByName(String name) {
		//return patientRepository.findByNameContainingIgnoreCase(name);
		return patientRepository.findByNameContainingIgnoreCaseAndEnabledTrue(name);
	}
	
	@Override
	public List<Patient> findByDOB(LocalDate locDate) {
		return patientRepository.findByDob(locDate);
	}

}
