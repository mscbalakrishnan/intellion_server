package com.example.demo.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.demo.domain.Appointment;

public class AppointmentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime time;
	private Long doctor;
	private Long patient;
	
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
	
	public Long getDoctor() {
		return doctor;
	}
	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}
	public Long getPatient() {
		return patient;
	}
	public void setPatient(Long patient) {
		this.patient = patient;
	}
	protected AppointmentDto() {
	}
	
	public AppointmentDto(LocalDateTime time, Long doctor, Long patient) {
		super();
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
	}
	public AppointmentDto(Appointment appointment) {
		if(appointment!=null){
			setId(appointment.getId());
			setTime(appointment.getTime());
			setPatient(appointment.getPatient().getId());
			setDoctor(appointment.getDoctor().getId());
		}
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", time=" + time + ", doctor=" + doctor + ", patient=" + patient + "]";
	}
}
