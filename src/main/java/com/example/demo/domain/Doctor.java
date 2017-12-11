package com.example.demo.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.domain.base.EntityWithSurrogatePK;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
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

	

}
