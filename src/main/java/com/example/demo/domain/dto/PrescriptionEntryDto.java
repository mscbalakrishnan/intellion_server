package com.example.demo.domain.dto;

import java.io.Serializable;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.MedicationUnit;
import com.example.demo.domain.PrescriptionEntry;

public class PrescriptionEntryDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private MedicationDto medicationDto;
	private PrescriptionDto prescriptionDto;
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
	public PrescriptionEntryDto(PrescriptionEntry p){
		setId(p.getId());
		setMedicationDto(new MedicationDto(p.getMedication()));
		setBeforeFood(p.isBeforeFood());
		setMorning(p.getMorning());
		setNight(p.getNight());
		setNoOfDays(p.getNoOfDays());
		setNoon(p.getNoon());
		setNotes(p.getNotes());
		setUnit(p.getUnit());
	}
	public static PrescriptionEntry dto2Pojo(PrescriptionEntryDto dto){
		PrescriptionEntry pojo = new PrescriptionEntry();
		pojo.setId(dto.getId());		
		pojo.setBeforeFood(dto.isBeforeFood());
		pojo.setMorning(dto.getMorning());
		pojo.setNoon(dto.getNoon());
		pojo.setNight(dto.getNight());
		pojo.setNoOfDays(dto.getNoOfDays());
		pojo.setNotes(dto.getNotes());
		pojo.setUnit(dto.getUnit());
		return pojo;
	}
	public MedicationDto getMedicationDto() {
		return medicationDto;
	}
	public void setMedicationDto(MedicationDto medicationDto) {
		this.medicationDto = medicationDto;
	}
	public PrescriptionDto getPrescriptionDto() {
		return prescriptionDto;
	}
	public void setPrescriptionDto(PrescriptionDto prescriptionDto) {
		this.prescriptionDto = prescriptionDto;
	}
	public PrescriptionEntryDto(){}
}
