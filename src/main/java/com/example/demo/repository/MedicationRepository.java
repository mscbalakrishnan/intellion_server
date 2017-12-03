package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Medication;

@Repository("medicationRepository")
public interface MedicationRepository extends CrudRepository<Medication, Long> {
	
	List<Medication> findByNameContainingIgnoreCase(String name);
//	Page<Appointment> findAll(Pageable pageable);
//	Page<Appointment> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);
//	City findByNameAndCountryAllIgnoringCase(String name, String country);
}
