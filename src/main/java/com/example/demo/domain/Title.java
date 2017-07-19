package com.example.demo.domain;

public enum Title {

	Mr("Mr."), Mrs("Mrs."), Ms("Ms."), Master("Master."), Baby("Baby."), Dr("Dr."), Prof("Prof.");

	private final String title;

	private Title(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public static Title getById(String titleStr) {
		for (Title type : Title.values()) {
			if (type.getTitle().equals(titleStr)) {
				return type;
			}
		}
		return null;
	}
}
