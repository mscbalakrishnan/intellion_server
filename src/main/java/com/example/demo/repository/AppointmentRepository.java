package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Appointment;

@Repository()
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

//	Page<Appointment> findAll(Pageable pageable);

//	Page<Appointment> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);

//	City findByNameAndCountryAllIgnoringCase(String name, String country);

}
