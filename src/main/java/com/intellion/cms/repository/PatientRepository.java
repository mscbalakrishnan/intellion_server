package com.intellion.cms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intellion.cms.domain.Patient;

@Repository()
public interface PatientRepository extends CrudRepository<Patient, String>{

	List<Patient> findByNameContaining(String name);
	List<Patient> findByNameContainingIgnoreCase(String name);
	List<Patient> findByDob(LocalDate currDate);
	List<Patient> findAllByEnabledTrueOrderByLastModifiedTimeDesc();
	List<Patient> findByNameContainingIgnoreCaseAndEnabledTrue(String name);
	


	@Modifying
	@Transactional
	@Query("update Patient p set p.enabled = :status where p.id = :id")
	int disablePatient(@Param("id") String patientId, @Param("status") boolean status);


}
