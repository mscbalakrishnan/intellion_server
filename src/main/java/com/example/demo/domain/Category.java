package com.example.demo.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@category_id")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="category_generator", sequenceName="category_sequence", initialValue = 23)
	@GeneratedValue(generator = "category_generator")
	private Long id;

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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", doctors=" + doctors + "]";
	}
	
}