package com.example.demo.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.example.demo.domain.base.EntityWithSurrogatePK;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category extends EntityWithSurrogatePK {

	@Column(nullable = false)
	private String name;

	public Category(String name) {
		this.name = name;
	}
	public Category() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonManagedReference	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "doctor_category",
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "doctor_id",referencedColumnName = "id"))
    private Set<Doctor> doctors;

	public Set<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", doctors=" + doctors + "]";
	}
	
}