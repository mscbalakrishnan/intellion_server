package com.example.demo.domain.dto;

import java.io.Serializable;

import com.example.demo.domain.MedicationUnit;
import com.example.demo.domain.PrescriptionEntry;

public class PrescriptionEntryInputDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long medicationDto;
	private Long prescriptionDto;
	private boolean beforeFood_morning;
	private boolean beforeFood_noon;
	private boolean beforeFood_night;
	private float morning;
	private float noon;
	private float night;
	private float noOfDays;
	private String notes;
	private MedicationUnit unit_morning; // numbers or milliliters (teaspoons)
	private MedicationUnit unit_noon; // numbers or milliliters (teaspoons)
	private MedicationUnit unit_night; // numbers or milliliters (teaspoons)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getMorning() {
		return morning;
	}
	public void setMorning(float morning) {
		this.morning = morning;
	}
	public float getNoon() {
		return noon;
	}
	public void setNoon(float noon) {
		this.noon = noon;
	}
	public float getNight() {
		return night;
	}
	public void setNight(float night) {
		this.night = night;
	}
	public float getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(float noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean isBeforeFood_morning() {
		return beforeFood_morning;
	}
	public void setBeforeFood_morning(boolean beforeFood_morning) {
		this.beforeFood_morning = beforeFood_morning;
	}
	public boolean isBeforeFood_noon() {
		return beforeFood_noon;
	}
	public void setBeforeFood_noon(boolean beforeFood_noon) {
		this.beforeFood_noon = beforeFood_noon;
	}
	public boolean isBeforeFood_night() {
		return beforeFood_night;
	}
	public void setBeforeFood_night(boolean beforeFood_night) {
		this.beforeFood_night = beforeFood_night;
	}
	public MedicationUnit getUnit_morning() {
		return unit_morning;
	}
	public void setUnit_morning(MedicationUnit unit_morning) {
		this.unit_morning = unit_morning;
	}
	public MedicationUnit getUnit_noon() {
		return unit_noon;
	}
	public void setUnit_noon(MedicationUnit unit_noon) {
		this.unit_noon = unit_noon;
	}
	public MedicationUnit getUnit_night() {
		return unit_night;
	}
	public void setUnit_night(MedicationUnit unit_night) {
		this.unit_night = unit_night;
	}
	public PrescriptionEntryInputDto(PrescriptionEntry p){
		setId(p.getId());
		setMedicationDto(p.getMedication().getId());
		setPrescriptionDto(p.getPrescription().getId());
		setBeforeFood_morning(p.isBeforeFood_morning());
		setBeforeFood_noon(p.isBeforeFood_noon());
		setBeforeFood_night(p.isBeforeFood_night());
		setUnit_morning(p.getUnit_morning());
		setUnit_noon(p.getUnit_noon());
		setUnit_night(p.getUnit_night());
	}
	public static PrescriptionEntry dto2Pojo(PrescriptionEntryInputDto dto){
		PrescriptionEntry pojo = new PrescriptionEntry();
		if (dto.getId()!=null) pojo.setId(dto.getId());
		pojo.setBeforeFood_morning(dto.isBeforeFood_morning());
		pojo.setBeforeFood_noon(dto.isBeforeFood_noon());
		pojo.setBeforeFood_night(dto.isBeforeFood_night());
		pojo.setMorning(dto.getMorning());
		pojo.setNoon(dto.getNoon());
		pojo.setNight(dto.getNight());
		pojo.setNoOfDays(dto.getNoOfDays());
		pojo.setNotes(dto.getNotes());
		pojo.setUnit_morning(dto.getUnit_morning());
		pojo.setUnit_noon(dto.getUnit_noon());
		pojo.setUnit_night(dto.getUnit_night());
		return pojo;
	}
	
	public PrescriptionEntryInputDto(){}
	public Long getMedicationDto() {
		return medicationDto;
	}
	public void setMedicationDto(Long medicationDto) {
		this.medicationDto = medicationDto;
	}
	public Long getPrescriptionDto() {
		return prescriptionDto;
	}
	public void setPrescriptionDto(Long prescriptionDto) {
		this.prescriptionDto = prescriptionDto;
	}
	@Override
	public String toString() {
		return "PrescriptionEntryInputDto [id=" + id + ", medicationDto=" + medicationDto + ", prescriptionDto="
				+ prescriptionDto + ", beforeFood_morning=" + beforeFood_morning + ", beforeFood_noon="
				+ beforeFood_noon + ", beforeFood_night=" + beforeFood_night + ", morning=" + morning + ", noon=" + noon
				+ ", night=" + night + ", noOfDays=" + noOfDays + ", notes=" + notes + ", unit_morning=" + unit_morning
				+ ", unit_noon=" + unit_noon + ", unit_night=" + unit_night + "]";
	}
}
