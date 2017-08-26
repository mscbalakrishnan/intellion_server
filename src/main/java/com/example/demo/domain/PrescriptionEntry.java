package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class PrescriptionEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "prescriptionentry_generator", sequenceName = "prescriptionentry_sequence", initialValue = 23)
	@GeneratedValue(generator = "prescriptionentry_generator")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medication_id")
	private Medication medication;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prescription_id")
	private Prescription prescription;
	
	private boolean beforeFood;
	private float morning;
	private float noon;
	private float night;
	private float noOfDays;
	private String notes;
	private MedicationUnit unit; // numbers or milliliters (teaspoons) 
	
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
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
	
	public MedicationUnit getUnit() {
		return unit;
	}
	public void setUnit(MedicationUnit unit) {
		this.unit = unit;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Medication getMedication() {
		return medication;
	}
	public void setMedication(Medication medication) {
		this.medication = medication;
	}
	public boolean isBeforeFood() {
		return beforeFood;
	}
	public void setBeforeFood(boolean beforeFood) {
		this.beforeFood = beforeFood;
	}
	public float getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(float noOfDays) {
		this.noOfDays = noOfDays;
	}
	public PrescriptionEntry(){}
	public PrescriptionEntry(Long id, Medication medication, Prescription prescription, boolean beforeFood,
			float morning, float noon, float night, float noOfDays, String notes, MedicationUnit unit) {
		super();
		this.id = id;
		this.medication = medication;
		this.prescription = prescription;
		this.beforeFood = beforeFood;
		this.morning = morning;
		this.noon = noon;
		this.night = night;
		this.noOfDays = noOfDays;
		this.notes = notes;
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "PrescriptionEntry [id=" + id + ", medication=" + medication + ", prescription=" + prescription
				+ ", beforeFood=" + beforeFood + ", morning=" + morning + ", noon=" + noon + ", night=" + night
				+ ", noOfDays=" + noOfDays + ", notes=" + notes + ", unit=" + unit + "]";
	}
}
