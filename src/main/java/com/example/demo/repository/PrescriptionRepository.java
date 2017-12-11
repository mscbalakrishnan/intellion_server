package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Prescription;

@Repository("prescriptionRepository")
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
	
	Iterable<Prescription> findByDoctor_Id(long doctorId);
	Iterable<Prescription> findByPatient_Id(String patientId);
	Iterable<Prescription> findByDoctor_IdAndPatient_Id(long doctorId, String patientId);
//	Page<Appointment> findAll(Pageable pageable);
//	Page<Appointment> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);
//	City findByNameAndCountryAllIgnoringCase(String name, String country);
}
