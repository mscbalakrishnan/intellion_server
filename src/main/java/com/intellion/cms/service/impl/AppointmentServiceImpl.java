package com.intellion.cms.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.Doctor;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.repository.AppointmentRepository;
import com.intellion.cms.repository.DoctorRepository;
import com.intellion.cms.repository.PatientRepository;
import com.intellion.cms.service.AppointmentService;

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
		/*List<Appointment> appointments = (List<Appointment>) findByDoctorNameAndPatientName(a.getDoctor().getName(), a.getPatient().getName());
		if (appointments != null && !appointments.isEmpty()) {
			for (Appointment appointment: appointments){
				if (a.getTime().equals(appointment.getTime())){
					return a;
				}
			}
		}*/
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
	public Appointment save(Long id, LocalDateTime time, Long doctorId, String patientId) {
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

	@Override
	public Iterable<Appointment> findByDoctorId(int doctorId) {
		return appointmentRepository.findByDoctor_Id(Long.valueOf(doctorId));
	}
	@Override
	public Iterable<Appointment> findByPatientId(String patientId) {
		return appointmentRepository.findByPatient_Id(patientId);
	}

	@Override
	public Iterable<Appointment> findByTimeBetween(LocalDate from, LocalDate to) {
		return appointmentRepository.findByTimeBetween(from.atStartOfDay(),to.atTime(23, 59));
	}

	@Override
	public Iterable<Appointment> findByTimeAfter(LocalDate from) {
		return appointmentRepository.findByTimeAfter(from.atStartOfDay());
	}

	@Override
	public Iterable<Appointment> findByTimeBefore(LocalDate upto) {
		return appointmentRepository.findByTimeBefore(upto.atTime(23,59));
	}

	@Override
	public Iterable<Appointment> findByDoctorName(String name) {
		return appointmentRepository.findByDoctor_Name(name);
	}
	@Override
	public Iterable<Appointment> findByDoctorNameAndPatientName(String doctorName, String patietName) {
		return appointmentRepository.findByDoctor_Name_AndPatient_Name(doctorName, patietName);
	}
}
