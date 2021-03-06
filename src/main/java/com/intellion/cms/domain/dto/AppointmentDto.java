package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.intellion.cms.domain.Appointment;

public class AppointmentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime time;
	private DoctorDto doctor;
	private PatientDto patient;
	private String doctorColorCode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public DoctorDto getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDto doctor) {
		this.doctor = doctor;
	}
	public PatientDto getPatient() {
		return patient;
	}
	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}
	protected AppointmentDto() {
	}
	
	public AppointmentDto(Appointment appointment) {
		if(appointment!=null){
			setId(appointment.getId());
			setTime(appointment.getTime());
			setPatient(new PatientDto(appointment.getPatient()));
			setDoctor(new DoctorDto(appointment.getDoctor()));
			setDoctorColorCode(appointment.getDoctor().getColorCode());
		}
	}
	public String getDoctorColorCode() {
		return doctorColorCode;
	}
	public void setDoctorColorCode(String doctorColorCode) {
		this.doctorColorCode = doctorColorCode;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", time=" + time + ", doctor=" + doctor + ", patient=" + patient + ", doctorColorCode=" + doctorColorCode +"]";
	}
}
