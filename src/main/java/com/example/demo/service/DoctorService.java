package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Doctor;

public interface DoctorService {
	Doctor getDoctor(String name);

	Doctor save(Doctor d);

	Iterable<Doctor> findAll();

	Optional<Doctor> findOne(long id);

	void delete(long id);

	void delete(Doctor doctor);

	List<Doctor> findByDoctorName(String name);
}
