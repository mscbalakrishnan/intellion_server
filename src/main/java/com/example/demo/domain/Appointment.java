package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@appointmen_id")
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="appointment_generator", sequenceName="appointment_sequence", initialValue = 23)
	@GeneratedValue(generator = "appointment_generator")
	private Long id;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	
	private LocalDateTime time;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
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
	protected Appointment() {
	}
	
	public Appointment(LocalDateTime time, Doctor doctor, Patient patient) {
		super();
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", time=" + time + ", doctor=" + doctor + ", patient=" + patient + "]";
	}
}
