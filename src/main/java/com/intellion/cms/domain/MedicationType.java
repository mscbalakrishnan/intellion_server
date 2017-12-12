package com.intellion.cms.domain;

public enum MedicationType {

	Tablet("Tablet"), Syrup("Syrup"), Capsule ("Capsule");

	private final String medicationType;

	private MedicationType(String medicationType) {
		this.medicationType = medicationType;
	}

	public String getMedicationType() {
		return medicationType;
	}

	public static MedicationType getById(String medicationTypeStr) {
		for (MedicationType type : MedicationType.values()) {
			if (type.getMedicationType().equals(medicationTypeStr)) {
				return type;
			}
		}
		return null;
	}
}
