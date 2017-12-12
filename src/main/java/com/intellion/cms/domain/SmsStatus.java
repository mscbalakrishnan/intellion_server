package com.intellion.cms.domain;

public enum SmsStatus {
	
	SUCCESS(1),FAILURE(2),NONE(3),PENDING(4),Unknown(100);
	
	private int id;
	private SmsStatus(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static SmsStatus getById(int id) {
		for(SmsStatus status : SmsStatus.values()) {
			if(status.getId() == id) {
				return status;
			}
		}
		
		return Unknown;
	}
	
	public static SmsStatus getByName(String value) {
		for(SmsStatus status : SmsStatus.values()) {
			if(status.name().equalsIgnoreCase(value)) {
				return status;
			}
		}
		
		return Unknown;
	}
}
