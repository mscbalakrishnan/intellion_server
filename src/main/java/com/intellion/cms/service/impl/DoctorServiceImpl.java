package com.intellion.cms.service.impl;

import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Doctor;
import com.intellion.cms.repository.DoctorRepository;
import com.intellion.cms.service.DoctorService;

@Component("doctorService")
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
		/*List<Doctor> list = findByDoctorName(d.getName());
		if (list.size() > 0) {
			//Alreaady Exists
			throw new IllegalArgumentException("Doctor already present");
		}*/
		Doctor d1 = null;
		try {
			d1 = doctorRepository.save(d);
		} catch (Exception e1) {
			Exception e = (Exception)e1.getCause();
			e = (Exception)e.getCause();
			if (e instanceof ConstraintViolationException){
				ConstraintViolationException e2 = (ConstraintViolationException) e;
				String errMsg = "";
				for (ConstraintViolation cv : e2.getConstraintViolations()){
					errMsg += cv.getMessageTemplate();
				}
				throw new IllegalArgumentException(errMsg);
			} else {
				throw e1;
			}
		}
		logger.debug("Saved Object is {}",d1);
		return d1;
	}
	@Override
	public Iterable<Doctor> findAll() {
		return doctorRepository.findAllByOrderByLastModifiedTimeDesc();
	}
	@Override
	public Doctor findOne(long id) {
		return doctorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}
	@Override
	public void delete(long id) {
		doctorRepository.deleteById(id);
	}
	@Override
	public void delete(Doctor doctor) {
		doctorRepository.delete(doctor);
	}
	@Override
	public List<Doctor> findByDoctorName(String name) {
		return doctorRepository.findByNameContainingIgnoreCase(name);
	}
}
