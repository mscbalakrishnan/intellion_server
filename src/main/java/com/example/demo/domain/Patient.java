package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@patient_id")
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="patient_generator", sequenceName="patient_sequence", initialValue = 23)
	@GeneratedValue(generator = "patient_generator")
	private Long id;
	private Title title;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String mobile;
	private String address1;
	private String address2;
	private String city;
	private String pincode;
	private String profileId;
	private String label;
	private String landline;
	private BloodGroup bloodGroup;
	private Gender gender;
	private String Occupation;
	private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dob;
	private int age;
	private String medicalHistory;
	private String medicalAlert;
	private String allergies;
	private boolean needWelcomeMessage;
	private boolean birthdayWish;
	private PeriodicRemainder remainder;
	public Patient() {
	}
	public Patient(String name) {
		super();
		this.name = name;
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
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return Occupation;
	}
	public void setOccupation(String occupation) {
		Occupation = occupation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	public String getMedicalAlert() {
		return medicalAlert;
	}
	public void setMedicalAlert(String medicalAlert) {
		this.medicalAlert = medicalAlert;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public boolean isNeedWelcomeMessage() {
		return needWelcomeMessage;
	}
	public void setNeedWelcomeMessage(boolean needWelcomeMessage) {
		this.needWelcomeMessage = needWelcomeMessage;
	}
	public boolean isBirthdayWish() {
		return birthdayWish;
	}
	public void setBirthdayWish(boolean birthdayWish) {
		this.birthdayWish = birthdayWish;
	}
	public PeriodicRemainder getRemainder() {
		return remainder;
	}
	public void setRemainder(PeriodicRemainder remainder) {
		this.remainder = remainder;
	}
	@OneToMany(mappedBy="patient",fetch=FetchType.LAZY)
	private Set<Appointment> appointments;
	
	public Set<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", title=" + title + ", name=" + name + ", mobile=" + mobile + ", address1="
				+ address1 + ", address2=" + address2 + ", city=" + city + ", pincode=" + pincode + ", profileId="
				+ profileId + ", label=" + label + ", landline=" + landline + ", bloodGroup=" + bloodGroup + ", gender="
				+ gender + ", Occupation=" + Occupation + ", email=" + email + ", dob=" + dob + ", age=" + age
				+ ", medicalHistory=" + medicalHistory + ", medicalAlert=" + medicalAlert + ", allergies=" + allergies
				+ ", needWelcomeMessage=" + needWelcomeMessage + ", birthdayWish=" + birthdayWish + ", remainder="
				+ remainder + ", appointments=" + appointments + "]";
	}
	
}
