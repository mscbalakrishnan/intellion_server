package com.example.demo.service;

import com.example.demo.domain.Doctor;

public interface DoctorService {
	Doctor getDoctor(String name);

	Doctor save(Doctor d);

	Iterable<Doctor> findAll();

	Doctor findOne(long id);

	void delete(long id);

	void delete(Doctor doctor);
}
