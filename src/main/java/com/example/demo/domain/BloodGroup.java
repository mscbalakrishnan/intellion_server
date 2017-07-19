package com.example.demo.domain;

public enum BloodGroup {
	APositive("A+"), ANegative("A-"), 
	BPositive("B+"), BNegative("B-"), 
	OPositive("O+"), ONegative("O-"), 
	ABPositive("AB+"), ABNegative("AB-");
	
	private final String bloodGroup;

	private BloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public static BloodGroup getById(String bloodGroupStr) {
		for (BloodGroup bloodGroup : BloodGroup.values()) {
			if (bloodGroup.getBloodGroup().equals(bloodGroupStr)) {
				return bloodGroup;
			}
		}
		return null;
	}
}
