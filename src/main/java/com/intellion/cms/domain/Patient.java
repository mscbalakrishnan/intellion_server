package com.intellion.cms.domain;

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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.intellion.cms.domain.base.BaseEntity;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class Patient extends BaseEntity {
	@Id
	@GenericGenerator(name = "sequence_patinet_id", strategy = "com.intellion.cms.domain.generator.PatientIdGenerator"/*,
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
	private String dentalHistory;
	private boolean needWelcomeMessage;
	private boolean birthdayWish;
	private PeriodicRemainder remainder;

	@OneToMany(mappedBy="patient",fetch=FetchType.LAZY)
	private Set<Appointment> appointments;
	
	public Serializable getPrimaryKey() {
		return getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public String getMobileNumber1() {
		return mobileNumber1;
	}

	public void setMobileNumber1(String mobileNumber1) {
		this.mobileNumber1 = mobileNumber1;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getDentalHistory() {
		return dentalHistory;
	}

	public void setDentalHistory(String dentalHistory) {
		this.dentalHistory = dentalHistory;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", title=" + title + ", name=" + name + ", guardianName=" + guardianName
				+ ", addressList=" + addressList + ", mobileNumber1=" + mobileNumber1 + ", mobileNumber2="
				+ mobileNumber2 + ", mobileNumber3=" + mobileNumber3 + ", profileId=" + profileId + ", label=" + label
				+ ", bloodGroup=" + bloodGroup + ", gender=" + gender + ", Occupation=" + Occupation + ", dob=" + dob
				+ ", email=" + email + ", age=" + age + ", medicalHistory=" + medicalHistory + ", medicalAlert="
				+ medicalAlert + ", allergies=" + allergies + ", needWelcomeMessage=" + needWelcomeMessage
				+ ", birthdayWish=" + birthdayWish + ", remainder=" + remainder + ", appointments=" + appointments + ", dentalHistory=" + dentalHistory
				+ "]";
	}

}
