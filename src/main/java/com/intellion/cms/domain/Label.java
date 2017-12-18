package com.intellion.cms.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "labelname" ))
public class Label extends EntityWithSurrogatePK{
	/**
	 * 
	 */
	private String labelname;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name="patient_id")
	private List<Patient> patientList = new ArrayList<>();
	
	


	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}	
	

}
