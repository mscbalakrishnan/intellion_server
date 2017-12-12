package com.intellion.cms.domain.dto;

import java.io.Serializable;

import com.intellion.cms.domain.Medication;
import com.intellion.cms.domain.MedicationType;

public class MedicationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private MedicationType type;
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
	public MedicationDto(){}
	
	public MedicationDto(Long id, String name, MedicationType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
	public MedicationDto(Medication p) {
		setId(p.getId());
		setName(p.getName());
		setType(p.getType());
	}
	public static Medication dto2Pojo(MedicationDto dto){
		Medication pojo = new Medication();
		pojo.setId(dto.getId());
		pojo.setName(dto.getName());
		pojo.setType(dto.getType());
		return pojo;
	}
	@Override
	public String toString() {
		return "MedicationDto [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
}
