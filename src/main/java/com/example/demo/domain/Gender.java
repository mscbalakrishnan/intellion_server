package com.example.demo.domain;

public enum Gender {
	Male("Male"), Female("Female"), Unisex("Unisex");
	private final String gender;

	private Gender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public static Gender getById(String genderStr) {
		for (Gender gender : Gender.values()) {
			if (gender.getGender().equals(genderStr)) {
				return gender;
			}
		}
		return null;
	}
}
