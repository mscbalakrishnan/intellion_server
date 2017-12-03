package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.domain.base.EntityWithSurrogatePK;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
public class Prescription extends EntityWithSurrogatePK {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date;
	
	@OneToMany(mappedBy="prescription",fetch=FetchType.EAGER)
	private Set<PrescriptionEntry> prescriptionEntries;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<PrescriptionEntry> getPrescriptionEntries() {
		return prescriptionEntries;
	}

	public void setPrescriptionEntries(Set<PrescriptionEntry> prescriptionEntries) {
		this.prescriptionEntries = prescriptionEntries;
	}

	@Override
	public String toString() {
		return "Prescription [doctor=" + doctor + ", patient=" + patient + ", date=" + date + ", prescriptionEntries="
				+ prescriptionEntries + "]";
	}

}
