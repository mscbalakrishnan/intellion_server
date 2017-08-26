package com.example.demo.domain.dto;

import java.io.Serializable;

import com.example.demo.domain.MedicationUnit;
import com.example.demo.domain.PrescriptionEntry;

public class PrescriptionEntryInputDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long medicationDto;
	private Long prescriptionDto;
	private boolean beforeFood;
	private float morning;
	private float noon;
	private float night;
	private float noOfDays;
	private String notes;
	private MedicationUnit unit; // numbers or milliliters (teaspoons)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isBeforeFood() {
		return beforeFood;
	}
	public void setBeforeFood(boolean beforeFood) {
		this.beforeFood = beforeFood;
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
	public MedicationUnit getUnit() {
		return unit;
	}
	public void setUnit(MedicationUnit unit) {
		this.unit = unit;
	}
	public PrescriptionEntryInputDto(PrescriptionEntry p){
		setId(p.getId());
		setMedicationDto(p.getMedication().getId());
		setPrescriptionDto(p.getPrescription().getId());
		setBeforeFood(p.isBeforeFood());
	}
	public static PrescriptionEntry dto2Pojo(PrescriptionEntryInputDto dto){
		PrescriptionEntry pojo = new PrescriptionEntry();
		if (dto.getId()!=null) pojo.setId(dto.getId());
//		pojo.setMedication(MedicationDto.dto2Pojo(dto.getMedicationDto());
//		pojo.setPrescription(dto.getPrescriptionDto());
		pojo.setBeforeFood(dto.isBeforeFood());
		pojo.setMorning(dto.getMorning());
		pojo.setNoon(dto.getNoon());
		pojo.setNight(dto.getNight());
		pojo.setNoOfDays(dto.getNoOfDays());
		pojo.setNotes(dto.getNotes());
		pojo.setUnit(dto.getUnit());
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
	
}
