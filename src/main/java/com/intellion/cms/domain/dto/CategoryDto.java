package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.Set;

import com.intellion.cms.domain.Category;

public class CategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public CategoryDto(String name) {
		this.name = name;
	}
	protected CategoryDto() {
	}
	public CategoryDto(Category category) {
		setName(category.getName());
		setId(category.getId());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    private Set<DoctorDto> doctors;
    public Set<DoctorDto> getDoctors() {
		return doctors;
	}
	public void setDoctors(Set<DoctorDto> doctors) {
		this.doctors = doctors;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}