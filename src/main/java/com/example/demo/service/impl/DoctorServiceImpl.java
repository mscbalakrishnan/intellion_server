package com.example.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Doctor;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.DoctorService;

@Component("doctorService")
//@Transactional

public class DoctorServiceImpl implements DoctorService{
	private final DoctorRepository doctorRepository;
	private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}
	@Override
	public Doctor getDoctor(String name) {
		return null;
	}
	@Override
	public Doctor save(Doctor d) {
		Doctor d1 = doctorRepository.save(d);
		logger.debug("Saved Object is {}",d1);
		return d1;
	}
	@Override
	public Iterable<Doctor> findAll() {
		return doctorRepository.findAll();
	}
	@Override
	public Doctor findOne(long id) {
		return doctorRepository.findOne(id);
	}
	@Override
	public void delete(long id) {
		doctorRepository.delete(id);
	}
	@Override
	public void delete(Doctor doctor) {
		doctorRepository.delete(doctor);
	}
	@Override
	public List<Doctor> findByDoctorName(String name) {
		return doctorRepository.findByNameContaining(name);
	}
}
