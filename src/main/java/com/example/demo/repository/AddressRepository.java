package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Address;

@Repository("addressRepository")
public interface AddressRepository extends CrudRepository<Address, String>{

//	List<Patient> findByNameContaining(String name);
//	List<Patient> findByNameContainingIgnoreCase(String name);
//	Page<Patient> findAll(Pageable pageable);
}
