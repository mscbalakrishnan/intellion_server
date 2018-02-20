package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

public class AppointmentInputDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private LocalDateTime time;
	private Long doctorId;
	private String patientId;
	private boolean smsToPatient;
	private boolean smsToDoctor;
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
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public boolean isSmsToPatient() {
		return smsToPatient;
	}
	public void setSmsToPatient(boolean smsToPatient) {
		this.smsToPatient = smsToPatient;
	}
	public boolean isSmsToDoctor() {
		return smsToDoctor;
	}
	public void setSmsToDoctor(boolean smsToDoctor) {
		this.smsToDoctor = smsToDoctor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
