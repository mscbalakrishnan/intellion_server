package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.demo.domain.base.EntityWithSurrogatePK;

@Entity
public class PrescriptionEntry extends EntityWithSurrogatePK {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medication_id")
	private Medication medication;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prescription_id")
	private Prescription prescription;
	
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

	public PrescriptionEntry() {}

	public Medication getMedication() {
		return medication;
	}
	public void setMedication(Medication medication) {
		this.medication = medication;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
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

	@Override
	public String toString() {
		return "PrescriptionEntry [medication=" + medication + ", prescription=" + prescription
				+ ", beforeFood_morning=" + beforeFood_morning + ", beforeFood_noon=" + beforeFood_noon
				+ ", beforeFood_night=" + beforeFood_night + ", morning=" + morning + ", noon=" + noon + ", night="
				+ night + ", noOfDays=" + noOfDays + ", notes=" + notes + ", unit_morning=" + unit_morning
				+ ", unit_noon=" + unit_noon + ", unit_night=" + unit_night + "]";
	}
	
}
