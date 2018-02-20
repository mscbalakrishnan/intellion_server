package com.intellion.cms.domain.dto;

import com.intellion.cms.domain.Address;

import lombok.Data;

public class AddressDto {
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
	public AddressDto() {}
	public AddressDto(Address address) {
		setId(address.getId());
		setApprtmentName(address.getApprtmentName());
		setStreetName(address.getStreetName());
		setArea(address.getArea());
		setCity(address.getCity());
		setPincode(address.getPincode());
		setCountry(address.getCountry());
		setLandLine1(address.getLandLine1());
		setLandLine2(address.getLandLine2());
		setLandLine3(address.getLandLine3());
	}
	
	public static Address Dto2Pojo(AddressDto address){
		Address pojo = new Address();
		pojo.setId(address.getId());
		pojo.setApprtmentName(address.getApprtmentName());
		pojo.setStreetName(address.getStreetName());
		pojo.setArea(address.getArea());
		pojo.setCity(address.getCity());
		pojo.setPincode(address.getPincode());
		pojo.setCountry(address.getCountry());
		pojo.setLandLine1(address.getLandLine1());
		pojo.setLandLine2(address.getLandLine2());
		pojo.setLandLine3(address.getLandLine3());
		return pojo;
	}
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
}
