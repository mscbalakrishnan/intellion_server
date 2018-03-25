package com.intellion.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intellion.cms.domain.Doctor;

@Repository("doctorRepository")
public interface DoctorRepository extends CrudRepository<Doctor, Long>{
	List<Doctor> findByNameContaining(String name);
	List<Doctor> findByNameContainingIgnoreCase(String name);
	List<Doctor> findByNameContainingIgnoreCaseAndEnabledTrue(String name);
	List<Doctor> findAllByEnabledTrueOrderByLastModifiedTimeDesc();
	@Modifying
	@Transactional
	@Query("update Doctor d set d.enabled = :status where d.id = :id")
	int disableDoctor(@Param("id") long doctorId, @Param("status") boolean status);
}
