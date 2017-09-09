package com.example.demo.domain.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.Title;

public class DoctorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Title title;
	private String name;
	private String email;
	private String qualification;
	private float fees;
	private String mobile;
    private Set<CategoryDto> categories;
	
	protected DoctorDto() {
	}
	
	public Set<CategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryDto> categories) {
		this.categories = categories;
	}
	
	private Set<AppointmentDto> appointments;
	
	public Set<AppointmentDto> getAppointment() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentDto> appointments) {
		this.appointments = appointments;
	}

	public DoctorDto(String name) {
		super();
		this.name = name;
	}
	
	public DoctorDto(String name, Title title, String email, String qualification, float fees, String mobile) {
		super();
		this.name = name;
		this.title = title;
		this.email = email;
		this.qualification = qualification;
		this.fees = fees;
		this.mobile = mobile;
	}

	public DoctorDto(Doctor doctor) {
		setId(doctor.getId());
		setName(doctor.getName());
		setTitle(doctor.getTitle());
		setEmail(doctor.getEmail());
		setQualification(doctor.getQualification());
		setFees(doctor.getFees());
		setMobile(doctor.getMobile());
		
	}
	public static Doctor Dto2Pojo(DoctorDto doctor){
		Doctor d = new Doctor();
		d.setName(doctor.getName());
		d.setId(doctor.getId());
		d.setName(doctor.getName());
		d.setTitle(doctor.getTitle());
		d.setEmail(doctor.getEmail());
		d.setQualification(doctor.getQualification());
		d.setFees(doctor.getFees());
		d.setMobile(doctor.getMobile());
		return d;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

}
