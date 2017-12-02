package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.Prescription;

@Repository()
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
	
	Iterable<Prescription> findByDoctor_Id(int doctorId);
	Iterable<Prescription> findByPatient_Id(int patId);
//	Page<Appointment> findAll(Pageable pageable);
//	Page<Appointment> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);
//	City findByNameAndCountryAllIgnoringCase(String name, String country);
}
