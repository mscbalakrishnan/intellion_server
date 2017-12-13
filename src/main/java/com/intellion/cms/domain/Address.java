package com.intellion.cms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

import lombok.Data;

@Entity
@Data
public class Address extends EntityWithSurrogatePK {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String apprtmentName;
	private String streetName;
	private String area;
	private String city;
	private String pincode;
	private String country;
	
	private String landLine1;
	private String landLine2;
	private String landLine3;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "patientId123", nullable = false)
//	private Patient patient;
}