package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Doctor;

@Repository()
public interface DoctorRepository extends CrudRepository<Doctor, Long>{

	List<Doctor> findByNameContaining(String name);
//	@Transactional
//	List<Doctor> findAll();
}
