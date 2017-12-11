package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class Patient extends BaseEntity {
	@Id
	@GenericGenerator(name = "sequence_patinet_id", strategy = "com.example.demo.domain.generator.PatientIdGenerator"/*,
			parameters = { @Parameter(name="tableName", value="patient") }*/)
	@GeneratedValue(generator="sequence_patinet_id") 
	private String id;
	
	private Title title;	
	@Column(nullable = false, unique=true)
	private String name;
	private String guardianName;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id")
	private List<Address> addressList = new ArrayList<Address>(0);
	@NotNull(message="Primary Mobile number can not be empty")
	@Size(min=10, max=10, message="Should be 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumber1;
	@Size(min=10, max=10, message="Should be 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumber2;
	@Size(min=10, max=10, message="Should be 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumber3;	
	private String profileId;
	private String label;
	private BloodGroup bloodGroup;
	private Gender gender;
	private String Occupation;
	@Past(message = "date of birth should have past date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dob;
	@Email(message = "Email should be valid")
	private String email;
	private int age;
	private String medicalHistory;
	private String medicalAlert;
	private String allergies;
	private boolean needWelcomeMessage;
	private boolean birthdayWish;
	private PeriodicRemainder remainder;

	@OneToMany(mappedBy="patient",fetch=FetchType.LAZY)
	private Set<Appointment> appointments;
	
	public Serializable getPrimaryKey() {
		return getId();
	}

}
