package com.intellion.cms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

import lombok.Data;

@Entity

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApprtmentName() {
		return apprtmentName;
	}
	public void setApprtmentName(String apprtmentName) {
		this.apprtmentName = apprtmentName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLandLine1() {
		return landLine1;
	}
	public void setLandLine1(String landLine1) {
		this.landLine1 = landLine1;
	}
	public String getLandLine2() {
		return landLine2;
	}
	public void setLandLine2(String landLine2) {
		this.landLine2 = landLine2;
	}
	public String getLandLine3() {
		return landLine3;
	}
	public void setLandLine3(String landLine3) {
		this.landLine3 = landLine3;
	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "patientId123", nullable = false)
//	private Patient patient;
}
