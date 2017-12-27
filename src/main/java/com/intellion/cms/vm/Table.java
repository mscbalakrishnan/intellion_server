package com.intellion.cms.vm;

import java.util.List;


public class Table {
	int border;
	String width;
	private String caption; 
	private int numberOfRows;
	private int numberOfColums; 
	List<PropertyHolder> propertyList;
	private Cell[] rowHeaderList;
	private Cell[] colHeaderList;
	
	private Cell[][] rowDataList;
	private String fontSize = "13px";
	
	
	public Table(int numberOfRows, int numberOfColums) {
		this.numberOfRows = numberOfRows;
		this.numberOfColums = numberOfColums;
		rowDataList = new Cell[numberOfRows][numberOfColums];
		
	}


	public int getBorder() {
		return border;
	}


	public void setBorder(int border) {
		this.border = border;
	}


	public String getWidth() {
		return width;
	}


	public void setWidth(String width) {
		this.width = width;
	}


	public int getNumberOfRows() {
		return numberOfRows;
	}


	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}


	public int getNumberOfColums() {
		return numberOfColums;
	}


	public void setNumberOfColums(int numberOfColums) {
		this.numberOfColums = numberOfColums;
	}


	public Cell[] getRowHeaderList() {
		return rowHeaderList;
	}


	public void setRowHeaderList(Cell[] rowHeaderList) {
		this.rowHeaderList = rowHeaderList;
	}


	public Cell[][] getRowDataList() {
		return rowDataList;
	}


	public void setRowDataList(Cell[][] rowDataList) {
		this.rowDataList = rowDataList;
	}


	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public Cell[] getColHeaderList() {
		return colHeaderList;
	}


	public void setColHeaderList(Cell[] colHeaderList) {
		this.colHeaderList = colHeaderList;
	}


	public List<PropertyHolder> getPropertyList() {
		return propertyList;
	}


	public void setPropertyList(List<PropertyHolder> propertyList) {
		this.propertyList = propertyList;
	}	

	public String getFontSize() {
		return fontSize;
	}


	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}	
}
