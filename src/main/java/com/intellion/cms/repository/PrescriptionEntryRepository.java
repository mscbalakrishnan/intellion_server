package com.intellion.cms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.PrescriptionEntry;

@Repository()
public interface PrescriptionEntryRepository extends CrudRepository<PrescriptionEntry, Long> {
//	Page<Appointment> findAll(Pageable pageable);
//	Page<Appointment> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);
//	City findByNameAndCountryAllIgnoringCase(String name, String country);
}
