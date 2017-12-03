package com.example.demo.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.demo.domain.base.EntityWithSurrogatePK;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Doctor extends EntityWithSurrogatePK {

	@Column(nullable = false, unique=true)
	private String name;
	
	private Title title;
	private String email;
	private String qualification;
	private float fees;
	private String mobile;
	
	@JsonBackReference	
	@ManyToMany(mappedBy = "doctors",fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Category> categories;
	
	public Doctor(){}
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	@OneToMany(mappedBy="doctor",fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<Appointment> appointments;

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Doctor(String name) {
		super();
		this.name = name;
	}
	
	public Doctor(String name, Title title, String email, String qualification, float fees, String mobile) {
		super();
		this.name = name;
		this.title = title;
		this.email = email;
		this.qualification = qualification;
		this.fees = fees;
		this.mobile = mobile;
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

	@Override
	public String toString() {
		return "Doctor [name=" + name + ", title=" + title + ", email=" + email + ", qualification=" + qualification
				+ ", fees=" + fees + ", mobile=" + mobile + ", categories=" + categories + ", appointments="
				+ appointments + "]";
	}

}
