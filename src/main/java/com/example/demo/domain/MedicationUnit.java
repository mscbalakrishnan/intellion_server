package com.example.demo.domain;

public enum MedicationUnit {
	Number("Number"), Teaspoon("Teaspoon"), Milliliter ("Milliliter");

	private final String medicationUnit;

	private MedicationUnit(String medicationUnit) {
		this.medicationUnit = medicationUnit;
	}

	public String getMedicationUnit() {
		return medicationUnit;
	}

	public static MedicationUnit getById(String medicationUnitStr) {
		for (MedicationUnit unitType : MedicationUnit.values()) {
			if (unitType.getMedicationUnit().equals(medicationUnitStr)) {
				return unitType;
			}
		}
		return null;
	}
}
