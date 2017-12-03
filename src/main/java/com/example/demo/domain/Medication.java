package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.example.demo.domain.base.EntityWithSurrogatePK;

@Entity
public class Medication extends EntityWithSurrogatePK {

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private MedicationType type; // tablet or syrup

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
	@Override
	public String toString() {
		return "Medication [name=" + name + ", type=" + type + "]";
	}
}
