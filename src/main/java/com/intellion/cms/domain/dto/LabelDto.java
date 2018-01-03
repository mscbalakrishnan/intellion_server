package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.intellion.cms.domain.Label;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;



public class LabelDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<String> patientIdList =  new ArrayList<>();
	private List<PatientDto> patientDtoList = new ArrayList<>();
	
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
	

	
	

	
	public List<PatientDto> getPatientDtoList() {
		return patientDtoList;
	}
	public void setPatientDtoList(List<PatientDto> patientDtoList) {
		this.patientDtoList = patientDtoList;
	}
	
	
	public List<String> getPatientIdList() {
		return patientIdList;
	}
	public void setPatientIdList(List<String> patientIdList) {
		this.patientIdList = patientIdList;
	}
	
	
	public LabelDto(Label label) {
		
		setId(label.getId());
		setName(label.getLabelname());
		
		List<PatientDto> tempPatientDtoList = new ArrayList<PatientDto>();
		List<Patient> patientListFromDomain = label.getPatientList();

		if(null != patientListFromDomain && patientListFromDomain.size() > 0){
			
			for(Patient patient:patientListFromDomain){
				PatientDto pDto = new PatientDto(patient);
				tempPatientDtoList.add(pDto);
			}
		}		
		setPatientDtoList(tempPatientDtoList);
		
	}	
	
	
	public static Label Dto2Pojo(LabelDto labelDto){
		
		Label label = new Label();
		List<Patient> patList = new ArrayList<Patient>();
		
		label.setId(labelDto.getId());
		
		return label;
	}	
	public LabelDto(){}
	
	public LabelDto(Long id, String name) {
		
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	


}
