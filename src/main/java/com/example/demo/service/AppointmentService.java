package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.domain.Appointment;

public interface AppointmentService {
	Appointment getAppointment(String name);

	Appointment save(Appointment a);
	Appointment save(Long id, LocalDateTime time,Long doctor,Long patient);
	Iterable<Appointment> findAll();

	Appointment findOne(long id);

	void delete(long id);

	void delete(Appointment appointment);

	Iterable<Appointment> findByDoctorId(int doctorId);
	Iterable<Appointment> findByDoctorName(String name);
	Iterable<Appointment> findByPatientId(int patientId);
	Iterable<Appointment> findByTimeBetween(LocalDate from, LocalDate to);
	Iterable<Appointment> findByTimeAfter(LocalDate from);
	Iterable<Appointment> findByTimeBefore(LocalDate upto);
}
