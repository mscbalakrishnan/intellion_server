package com.example.demo.domain.dto;

import com.example.demo.domain.Address;

import lombok.Data;
@Data
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
}
