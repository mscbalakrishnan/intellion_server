package com.intellion.cms.service;

import java.util.List;

import com.intellion.cms.domain.Doctor;

public interface DoctorService {
	Doctor getDoctor(String name);

	Doctor save(Doctor d);

	Iterable<Doctor> findAll();

	Doctor findOne(long id);

	void delete(long id);

	void delete(Doctor doctor);

	List<Doctor> findByDoctorName(String name);
}
