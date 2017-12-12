package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.Set;

import com.intellion.cms.domain.Doctor;
import com.intellion.cms.domain.Title;

import lombok.Data;
@Data
public class DoctorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Title title;
	private String email;
	private String qualification;
	private float fees;
	private String mobile;
    private Set<CategoryDto> categories;
	private Set<AppointmentDto> appointments;
	protected DoctorDto() {
	}
	public DoctorDto(Doctor doctor) {
		setId(doctor.getId());
		setName(doctor.getName());
		setTitle(doctor.getTitle());
		setEmail(doctor.getEmail());
		setQualification(doctor.getQualification());
		setFees(doctor.getFees());
		setMobile(doctor.getMobile1());
	}
	public static Doctor Dto2Pojo(DoctorDto doctor){
		Doctor d = new Doctor();
		d.setName(doctor.getName());
		d.setId(doctor.getId());
		d.setName(doctor.getName());
		d.setTitle(doctor.getTitle());
		d.setEmail(doctor.getEmail());
		d.setQualification(doctor.getQualification());
		d.setFees(doctor.getFees());
		d.setMobile1(doctor.getMobile());
		return d;
	}
}
