package com.intellion.cms.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.intellion.cms.domain.base.EntityWithSurrogatePK;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class Doctor extends EntityWithSurrogatePK {

	@Column(nullable = false, unique=true)
	private String name;
	
	private Title title;
	@Email(message = "Email should be valid")
	private String email;
	private String qualification;
	private float fees;
	@NotNull(message="Mobile number can not be empty")
	@Size(min=10, max=10, message="Should be 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobile1;
	@Size(min=10, max=10, message="Should be 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumber2;
	@Size(min=10, max=10, message="Should be 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumber3;
	
	@JsonBackReference	
	@ManyToMany(mappedBy = "doctors",fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Category> categories;
	
	@OneToMany(mappedBy="doctor",fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<Appointment> appointments;

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

	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobileNumber2() {
		return mobileNumber2;
	}

	public void setMobileNumber2(String mobileNumber2) {
		this.mobileNumber2 = mobileNumber2;
	}

	public String getMobileNumber3() {
		return mobileNumber3;
	}

	public void setMobileNumber3(String mobileNumber3) {
		this.mobileNumber3 = mobileNumber3;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Doctor [name=" + name + ", title=" + title + ", email=" + email + ", qualification=" + qualification
				+ ", fees=" + fees + ", mobile1=" + mobile1 + ", mobileNumber2=" + mobileNumber2 + ", mobileNumber3="
				+ mobileNumber3 + ", categories=" + categories + ", appointments=" + appointments + "]";
	}

	

}
