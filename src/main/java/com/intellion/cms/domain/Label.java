package com.intellion.cms.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "labelname"))
public class Label extends EntityWithSurrogatePK {
	private String labelname;

	@ManyToMany(mappedBy = "labels", fetch=FetchType.EAGER)
	private Set<Patient> patientList;

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public Set<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(Set<Patient> patientList) {
		this.patientList = patientList;
	}

}
