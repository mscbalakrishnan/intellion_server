package com.intellion.cms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.intellion.cms.domain.Appointment;

public interface AppointmentService {
	Appointment getAppointment(String name);

	Appointment save(Appointment a);
	Appointment save(Long id, LocalDateTime time,Long doctor,String patient);
	Iterable<Appointment> findAll();

	Appointment findOne(long id);

	void delete(long id);

	void delete(Appointment appointment);

	Iterable<Appointment> findByDoctorId(int doctorId);
	Iterable<Appointment> findByDoctorName(String name);
	Iterable<Appointment> findByPatientId(String patientId);
	Iterable<Appointment> findByTimeBetween(LocalDate from, LocalDate to);
	Iterable<Appointment> findByTimeAfter(LocalDate from);
	Iterable<Appointment> findByTimeBefore(LocalDate upto);
	Iterable<Appointment> findByDoctorNameAndPatientName(String doctorName, String patietName);
	Iterable<Appointment> findByTimeBetweenAndDoctorNameOrPatientName(LocalDateTime from, LocalDateTime to, String doctorName, String patietName);
}
