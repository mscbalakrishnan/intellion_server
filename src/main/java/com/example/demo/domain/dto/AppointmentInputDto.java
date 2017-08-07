package com.example.demo.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.demo.domain.Appointment;

public class AppointmentInputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime time;
	private Long doctorId;
	private Long patientId;
	
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
	
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	protected AppointmentInputDto() {
	}
	public AppointmentInputDto(Long id, LocalDateTime time, Long doctorId, Long patientId) {
		super();
		this.id = id;
		this.time = time;
		this.doctorId = doctorId;
		this.patientId = patientId;
	}
	@Override
	public String toString() {
		return "AppointmentInputDto [id=" + id + ", time=" + time + ", doctorId=" + doctorId + ", patientId="
				+ patientId + "]";
	}
}
