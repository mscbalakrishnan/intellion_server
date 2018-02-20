package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.Set;

import com.intellion.cms.domain.Doctor;
import com.intellion.cms.domain.Title;

import lombok.Data;

public class DoctorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Title title;
	private int titleId;
	private String email;
	private String qualification;
	private String additionalQualification;
	private float fees;
	private String mobile;
	private String categoryId;
	private String colorCode;
	private Set<AppointmentDto> appointments;
	protected DoctorDto() {
	}
	public DoctorDto(Doctor doctor) {
		setId(doctor.getId());
		setName(doctor.getName());
		setTitle(doctor.getTitle());
		setTitleId(doctor.getTitle().getId());
		setEmail(doctor.getEmail());
		setQualification(doctor.getQualification());
		setAdditionalQualification(doctor.getAdditionalQualification());
		setFees(doctor.getFees());
		setMobile(doctor.getMobile1());
		setCategoryId(doctor.getCategoryId());
		setColorCode(doctor.getColorCode());
	}
	public static Doctor Dto2Pojo(DoctorDto doctor){
		Doctor d = new Doctor();
		d.setName(doctor.getName());
		d.setId(doctor.getId());
		d.setTitle(doctor.getTitle());
		d.setEmail(doctor.getEmail());
		d.setQualification(doctor.getQualification());
		d.setAdditionalQualification(doctor.getAdditionalQualification());
		d.setFees(doctor.getFees());
		d.setMobile1(doctor.getMobile());
		d.setCategoryId(doctor.getCategoryId());
		d.setColorCode(doctor.getColorCode());
		return d;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAdditionalQualification() {
		return additionalQualification;
	}
	public void setAdditionalQualification(String additionalQualification) {
		this.additionalQualification = additionalQualification;
	}
	public float getFees() {
		return fees;
	}
	public void setFees(float fees) {
		this.fees = fees;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public Set<AppointmentDto> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<AppointmentDto> appointments) {
		this.appointments = appointments;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
