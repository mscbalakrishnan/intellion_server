package com.example.demo.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AppointmentInputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime time;
	private Long doctorId;
	private Long patientId;
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
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
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
	protected AppointmentInputDto() {
	}
	public AppointmentInputDto(Long id, LocalDateTime time, Long doctorId, Long patientId, boolean smsToDoctor, boolean smsToPatient) {
		super();
		this.id = id;
		this.time = time;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.smsToDoctor = smsToDoctor;
		this.smsToPatient = smsToPatient;
	}
	@Override
	public String toString() {
		return "AppointmentInputDto [id=" + id + ", time=" + time + ", doctorId=" + doctorId + ", patientId="
				+ patientId + ", smsToPatient=" + smsToPatient + ", smsToDoctor=" + smsToDoctor + "]";
	}
}
