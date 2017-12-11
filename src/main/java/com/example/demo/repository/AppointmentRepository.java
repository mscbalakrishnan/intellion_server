package com.example.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Appointment;

@Repository("appointmentRepository")
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	
	Iterable<Appointment> findByDoctor_Id(Long doctorId);
	Iterable<Appointment> findByDoctor_Name(String name);
	Iterable<Appointment> findByPatient_Id(String patientId);
	Iterable<Appointment> findByTimeBetween(LocalDateTime from, LocalDateTime to);
	Iterable<Appointment> findByTimeAfter(LocalDateTime from);
	Iterable<Appointment> findByTimeBefore(LocalDateTime upto);
	Iterable<Appointment> findByDoctor_Name_AndPatient_Name(String doctorName, String patietName);
//	Page<Appointment> findAll(Pageable pageable);
	

//	Page<Appointment> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);

//	City findByNameAndCountryAllIgnoringCase(String name, String country);

}
