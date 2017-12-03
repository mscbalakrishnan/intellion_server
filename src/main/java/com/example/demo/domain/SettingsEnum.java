package com.example.demo.domain;

public interface SettingsEnum {
	public static enum TYPE {
		GLOBAL, APPLICATION
	}

	public static enum CATEGORY {
		SMS
	}

	public static enum PROPERTY {
		ENABLED, REQUIRED
	}
}
