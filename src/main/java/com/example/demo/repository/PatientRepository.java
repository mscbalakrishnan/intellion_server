package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Patient;

@Repository()
public interface PatientRepository extends CrudRepository<Patient, Long>{

	List<Patient> findByNameContaining(String name);
	
//	Page<Patient> findAll(Pageable pageable);
}
