package com.intellion.cms.vm;

import com.itextpdf.text.BaseColor;

public class Cell {
	
	public Cell(String value) {
		this.value = value;
	}
	private String value;
	private String color = BaseColor.BLACK.toString();
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return value;
	}
}
