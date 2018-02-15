package com.intellion.cms.domain;

public enum Title{

	Mr(0), Mrs(1), Ms(2), Master(3), Baby(4), Dr(5), Prof(6),Unknown(100);;


	private int id;
	
	private Title(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}	
	
	
	
	public static Title getById(int id) {
		for(Title bg : Title.values()) {
			if(bg.getId() == id) {
				return bg;
			}
		}
		
		return Unknown;
	}
	
	public static Title getByName(String value) {
		for(Title bg : Title.values()) {
			if(bg.name().equalsIgnoreCase(value)) {
				return bg;
			}
		}
		
		return Unknown;
	}
}
