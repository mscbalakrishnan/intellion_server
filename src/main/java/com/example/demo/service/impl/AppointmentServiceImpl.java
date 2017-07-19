package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.AppointmentService;

@Component("appointmentService")
//@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	
	public AppointmentServiceImpl(AppointmentRepository appointmentRepository,DoctorRepository doctorRepository, PatientRepository patientRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}
	
	@Override
	public Appointment getAppointment(String name) {
		return null;
	}

	@Override
	public Appointment save(Appointment a) {
		return appointmentRepository.save(a);
	}

	@Override
	public Iterable<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment findOne(long id) {
		return appointmentRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		appointmentRepository.delete(id);
	}

	@Override
	public void delete(Appointment appointment) {
		appointmentRepository.delete(appointment);
	}

	@Override
	public Appointment save(Long id, LocalDateTime time, Long doctorId, Long patientId) {
		Doctor doctor = doctorRepository.findOne(doctorId);
		Patient patient = patientRepository.findOne(patientId);
		if (doctor == null) {
			throw new IllegalArgumentException("Doctor Id does not exists:" + doctorId);
		}
		if (patient == null) {
			throw new IllegalArgumentException("Patient Id does not exists:" + patientId);
		}
		if (id == null) {
			Appointment appointment = new Appointment(time, doctor, patient);
			return appointmentRepository.save(appointment);
		} else {
			Appointment appointment = appointmentRepository.findOne(id);
			if (appointment != null) {
				appointment.setTime(time);
				appointment.setDoctor(doctor);
				appointment.setPatient(patient);
				return appointmentRepository.save(appointment);
			} else {
				throw new IllegalArgumentException("Appointment Id does not exists:" + id);
			}
		}
	}
}
