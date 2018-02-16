package com.intellion.cms.service;

import com.intellion.cms.domain.Prescription;

public interface PrescriptionService {
	Prescription save(Prescription a);
	Iterable<Prescription> findAll();
	Prescription findOne(long id);
	void delete(long id);
	Iterable<Prescription> findByDoctor_Id(long doctorId);
	Iterable<Prescription> findByPatient_Id(String patId);
	Iterable<Prescription> findByDoctor_IdAndPatient_Id(long doctorId, String patientId);
	
}
