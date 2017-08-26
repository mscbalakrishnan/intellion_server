package com.example.demo.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class PrescriptionInputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long doctorId;
	private Long patientId;
	private LocalDate date;
	private Set<PrescriptionEntryInputDto> prescriptionEntries;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public Set<PrescriptionEntryInputDto> getPrescriptionEntries() {
		return prescriptionEntries;
	}
	public void setPrescriptionEntries(Set<PrescriptionEntryInputDto> prescriptionEntries) {
		this.prescriptionEntries = prescriptionEntries;
	}
	public PrescriptionInputDto() {
	}
}
