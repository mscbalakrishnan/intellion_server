package com.intellion.cms.domain;

public enum BloodGroup {
	Apositive(0),Anegative(1),Bpositive(2),Bnegative(3),Opositive(4),Onegative(5),ABpositive(6),ABnegative(7),Unknown(100);
	
	/*private final String bloodGroup;

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
	}*/
	
	private int id;
	private BloodGroup(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static BloodGroup getById(int id) {
		for(BloodGroup bg : BloodGroup.values()) {
			if(bg.getId() == id) {
				return bg;
			}
		}
		
		return Unknown;
	}
	
	public static BloodGroup getByName(String value) {
		for(BloodGroup bg : BloodGroup.values()) {
			if(bg.name().equalsIgnoreCase(value)) {
				return bg;
			}
		}
		
		return Unknown;
	}
}
