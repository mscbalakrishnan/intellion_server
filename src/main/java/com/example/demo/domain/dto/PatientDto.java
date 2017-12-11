package com.example.demo.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.domain.Address;
import com.example.demo.domain.Appointment;
import com.example.demo.domain.BloodGroup;
import com.example.demo.domain.Gender;
import com.example.demo.domain.Patient;
import com.example.demo.domain.PeriodicRemainder;
import com.example.demo.domain.Title;

import lombok.Data;
@Data
public class PatientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Title title;
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
		setMobile(patient.getMobileNumber1());
		setProfileId(patient.getProfileId());
		setLabel(patient.getLabel());
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
		p.setLabel(patient.getLabel());
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
	
}
