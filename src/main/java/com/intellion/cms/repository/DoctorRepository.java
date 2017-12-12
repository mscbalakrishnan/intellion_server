package com.intellion.cms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.Doctor;

@Repository("doctorRepository")
public interface DoctorRepository extends CrudRepository<Doctor, Long>{
	List<Doctor> findByNameContaining(String name);
	List<Doctor> findByNameContainingIgnoreCase(String name);
}
