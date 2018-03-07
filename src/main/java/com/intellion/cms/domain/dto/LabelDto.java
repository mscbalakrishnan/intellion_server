package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.intellion.cms.domain.Label;
import com.intellion.cms.domain.Patient;

public class LabelDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Set<String> patientIdList = new HashSet<>();
	
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

	public Set<String> getPatientIdList() {
		return patientIdList;
	}

	public void setPatientIdList(Set<String> patientIdList) {
		this.patientIdList = patientIdList;
	}
	public LabelDto(){
		
	}
	public LabelDto(Label label) {
		setId(label.getId());
		setName(label.getLabelname());
		for (Patient patient : label.getPatientList()) {
			getPatientIdList().add(patient.getId());
		}
	}

	public static Label Dto2Pojo(LabelDto labelDto) {
		Label label = new Label();
		Set<String> patientIdList = new HashSet<String>();
		label.setId(labelDto.getId());
		for (String patientId : labelDto.getPatientIdList()) {
			patientIdList.add(patientId);
		}
//		label.setPatientList(patientIdList);
		return label;
	}

	@Override
	public String toString() {
		return "LabelDto [id=" + id + ", name=" + name + ", patientIdList=" + patientIdList + "]";
	}


}
