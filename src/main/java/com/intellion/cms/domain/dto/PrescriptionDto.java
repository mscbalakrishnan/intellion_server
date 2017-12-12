package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.intellion.cms.domain.Prescription;

public class PrescriptionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private DoctorDto doctorDto;
	private PatientDto patientDto;
	private LocalDate date;
	private Set<PrescriptionEntryDto> prescriptionEntries;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DoctorDto getDoctorDto() {
		return doctorDto;
	}
	public void setDoctorDto(DoctorDto doctorDto) {
		this.doctorDto = doctorDto;
	}
	public PatientDto getPatientDto() {
		return patientDto;
	}
	public void setPatientDto(PatientDto patientDto) {
		this.patientDto = patientDto;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Set<PrescriptionEntryDto> getPrescriptionEntries() {
		return prescriptionEntries;
	}
	public void setPrescriptionEntries(Set<PrescriptionEntryDto> prescriptionEntries) {
		this.prescriptionEntries = prescriptionEntries;
	}
	public PrescriptionDto(Long id, DoctorDto doctorDto, PatientDto patientDto, LocalDate date,
			Set<PrescriptionEntryDto> prescriptionEntries) {
		super();
		this.id = id;
		this.doctorDto = doctorDto;
		this.patientDto = patientDto;
		this.date = date;
		this.prescriptionEntries = prescriptionEntries;
	}
	@Override
	public String toString() {
		return "PrescriptionDto [id=" + id + ", doctorDto=" + doctorDto + ", patientDto=" + patientDto + ", date="
				+ date + ", prescriptionEntries=" + prescriptionEntries + "]";
	}
	public PrescriptionDto() {
	}
	public PrescriptionDto(Prescription p) {
		setId(p.getId());
		setDoctorDto(new DoctorDto(p.getDoctor()));
		setPatientDto(new PatientDto(p.getPatient()));
		setDate(p.getDate());
		Set<PrescriptionEntryDto> list = new HashSet<>();
		p.getPrescriptionEntries().forEach(x -> list.add(new PrescriptionEntryDto(x)));
		setPrescriptionEntries(list);
	}
}
