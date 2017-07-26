package com.example.demo.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.BloodGroup;
import com.example.demo.domain.Gender;
import com.example.demo.domain.Patient;
import com.example.demo.domain.PeriodicRemainder;
import com.example.demo.domain.Title;

public class PatientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Title title;
	private String name;
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
	/*@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)*/
	private LocalDate dob;
	private int age;
	private String medicalHistory;
	private String medicalAlert;
	private String allergies;
	private boolean needWelcomeMessage;
	private boolean birthdayWish;
	private PeriodicRemainder remainder;
	protected PatientDto() {
	}
	public PatientDto(String name) {
		super();
		this.name = name;
	}
	public PatientDto(Patient patient) {
		setId(patient.getId());
		setTitle(patient.getTitle());
		setName(patient.getName());
		setMobile(patient.getMobile());
		setAddress1(patient.getAddress1());
		setAddress2(patient.getAddress2());
		setCity(patient.getCity());
		setPincode(patient.getPincode());
		setProfileId(patient.getProfileId());
		setLabel(patient.getLabel());
		setLandline(patient.getLandline());
		setBloodGroup(patient.getBloodGroup());
		setGender(patient.getGender());
		setOccupation(patient.getOccupation());
		setEmail(patient.getEmail());
		setDob(patient.getDob());
		if(patient.getAge() == 0 && patient.getDob() != null){
			setAge(Period.between(patient.getDob(), LocalDate.now()).getYears());
		} else {
			setAge(patient.getAge());
		}		
		setMedicalHistory(patient.getMedicalHistory());
		setMedicalAlert(patient.getMedicalAlert());
		setAllergies(patient.getAllergies());
		setNeedWelcomeMessage(patient.isNeedWelcomeMessage());
		setBirthdayWish(patient.isBirthdayWish());
		setRemainder(patient.getRemainder());
		Set<Appointment> appointments = patient.getAppointments();
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		for (Appointment appointment:appointments){
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		setAppointments(appointmentDtos);
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
	
	private Set<AppointmentDto> appointments;
	
	public Set<AppointmentDto> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<AppointmentDto> appointments) {
		this.appointments = appointments;
	}
	public static Patient Dto2Pojo(PatientDto patient){
		Patient p = new Patient();
		p.setId(patient.getId());
		p.setTitle(patient.getTitle());
		p.setName(patient.getName());
		p.setMobile(patient.getMobile());
		p.setAddress1(patient.getAddress1());
		p.setAddress2(patient.getAddress2());
		p.setCity(patient.getCity());
		p.setPincode(patient.getPincode());
		p.setProfileId(patient.getProfileId());
		p.setLabel(patient.getLabel());
		p.setLandline(patient.getLandline());
		p.setBloodGroup(patient.getBloodGroup());
		p.setGender(patient.getGender());
		p.setOccupation(patient.getOccupation());
		p.setEmail(patient.getEmail());
		p.setDob(patient.getDob());
		p.setAge(patient.getAge());		
		p.setMedicalHistory(patient.getMedicalHistory());
		p.setMedicalAlert(patient.getMedicalAlert());
		p.setAllergies(patient.getAllergies());
		p.setNeedWelcomeMessage(patient.isNeedWelcomeMessage());
		p.setBirthdayWish(patient.isBirthdayWish());
		p.setRemainder(patient.getRemainder());
		return p;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", title=" + title + ", name=" + name + ", mobile=" + mobile + ", address1="
				+ address1 + ", address2=" + address2 + ", city=" + city + ", pincode=" + pincode + ", profileId="
				+ profileId + ", label=" + label + ", landline=" + landline + ", bloodGroup=" + bloodGroup + ", gender="
				+ gender + ", Occupation=" + Occupation + ", email=" + email + ", dob=" + dob + ", age=" + age
				+ ", medicalHistory=" + medicalHistory + ", medicalAlert=" + medicalAlert + ", allergies=" + allergies
				+ ", needWelcomeMessage=" + needWelcomeMessage + ", birthdayWish=" + birthdayWish + ", remainder="
				+ remainder + "]";
	}
}
