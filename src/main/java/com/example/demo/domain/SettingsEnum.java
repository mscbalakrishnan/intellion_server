package com.example.demo.domain;

public interface SettingsEnum {
	public static enum TYPE {
		GLOBAL
	}

	public static enum CATEGORY {
		SMS, CLINIC
	}

	public static enum PROPERTY {
		ENABLED, USERNAME, PASSWORD, SENDER, NAME, ADDRESS, PHONENO, MOBILENO, URL, TYPE, PRIORITY
	}
}
