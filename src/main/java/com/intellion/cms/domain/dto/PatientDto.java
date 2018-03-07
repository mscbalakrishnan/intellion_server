package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.intellion.cms.domain.Address;
import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.BloodGroup;
import com.intellion.cms.domain.Gender;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.PeriodicRemainder;
import com.intellion.cms.domain.Title;

public class PatientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Title title;
	private int titleId;
	private String name;
	@NotNull(message="Primary Mobile number can not be empty")
	@Size(min=10, max=10, message="Should be 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobile;
	private String address1;
	private String address2;
	private String city;
	private String pincode;
	private String profileId;
	private String label;
	private String landline;
	private BloodGroup bloodGroup;
	private int bloodGroupId;
	private Gender gender;
	private String Occupation;
	@Email(message = "Email should be valid")
	private String email;
	/*@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)*/
	private LocalDate dob;
	private int age;
	private String medicalHistory;
	private String medicalAlert;
	private String allergies;
	private String dentalHistory;
	
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
		setTitleId(patient.getTitle().getId());
		setName(patient.getName());
		setMobile(patient.getMobileNumber1());
		setProfileId(patient.getProfileId());
//		setLabel(patient.getLabel());
		setBloodGroupId(patient.getBloodGroup().getId());
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
		setDentalHistory(patient.getDentalHistory());
		setMedicalAlert(patient.getMedicalAlert());
		setAllergies(patient.getAllergies());
		setNeedWelcomeMessage(patient.isNeedWelcomeMessage());
		setBirthdayWish(patient.isBirthdayWish());
		setRemainder(patient.getRemainder());
		if (patient.getAddressList() != null && patient.getAddressList().size()>0){
			Address address = patient.getAddressList().get(0);
			AddressDto addressDto = new AddressDto(address);
			setCity(addressDto.getCity());
			setPincode(addressDto.getPincode());
			setAddress1(addressDto.getApprtmentName());
			setAddress2(addressDto.getArea());
			setLandline(addressDto.getLandLine1());
		}
		Set<Appointment> appointments = patient.getAppointments();
		setProfileId(patient.getId());
		/*Set<AppointmentDto> appointmentDtos = new HashSet<>();
		for (Appointment appointment:appointments){
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		setAppointments(appointmentDtos);*/
	}
	
	public static Patient Dto2Pojo(PatientDto patient){
		Patient p = new Patient();
		p.setId(patient.getId());
		p.setTitle(patient.getTitle());
		p.setName(patient.getName());
		p.setMobileNumber1(patient.getMobile());
		p.setProfileId(patient.getProfileId());
//		p.setLabel(patient.getLabel());
		p.setBloodGroup(patient.getBloodGroup());
		p.setGender(patient.getGender());
		p.setOccupation(patient.getOccupation());
		p.setEmail(patient.getEmail());
		p.setDob(patient.getDob());
		p.setAge(patient.getAge());		
		p.setMedicalHistory(patient.getMedicalHistory());
		p.setDentalHistory(patient.getDentalHistory());
		p.setMedicalAlert(patient.getMedicalAlert());
		p.setAllergies(patient.getAllergies());
		p.setNeedWelcomeMessage(patient.isNeedWelcomeMessage());
		p.setBirthdayWish(patient.isBirthdayWish());
		p.setRemainder(patient.getRemainder());
		
		return p;
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
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
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
	public int getBloodGroupId() {
		return bloodGroupId;
	}
	public void setBloodGroupId(int bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
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
	public String getDentalHistory() {
		return dentalHistory;
	}
	public void setDentalHistory(String dentalHistory) {
		this.dentalHistory = dentalHistory;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
