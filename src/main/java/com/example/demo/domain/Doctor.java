package com.example.demo.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@doctor_id")
public class Doctor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="doctor_generator", sequenceName="doctor_sequence", initialValue = 23)
	@GeneratedValue(generator = "doctor_generator")
	private Long id;

	@Column(nullable = false)
	private String name;
	
	private Title title;
	private String email;
	private String qualification;
	private float fees;
	private String mobile;
	
	@JsonBackReference
	@ManyToMany(mappedBy = "doctors",fetch=FetchType.LAZY)
    private Set<Category> categories;
	
	public Doctor(){}
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	@JsonBackReference
	@OneToOne(mappedBy="doctor",fetch=FetchType.LAZY)
	private Appointment appointment;
	
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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
