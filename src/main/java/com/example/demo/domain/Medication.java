package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
public class Medication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="medication_generator", sequenceName="medication_sequence", initialValue = 23)
	@GeneratedValue(generator = "medication_generator")
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private MedicationType type; // tablet or syrup
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public MedicationType getType() {
		return type;
	}
	public void setType(MedicationType type) {
		this.type = type;
	}
	public Medication() {}
	
	public Medication(Long id, String name, MedicationType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
}
