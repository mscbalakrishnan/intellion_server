package com.intellion.cms.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.Appointment;

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
	@Query("select a from Appointment a where (a.time between :from and :to) and (a.doctor.name=:doctorName or a.patient.name=:patietName)")
	Iterable<Appointment> findByTimeBetweenAndDoctorNameOrPatientName123(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, @Param("doctorName") String doctorName, @Param("patietName") String patietName);
	

//	Page<Appointment> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);

//	City findByNameAndCountryAllIgnoringCase(String name, String country);

}
