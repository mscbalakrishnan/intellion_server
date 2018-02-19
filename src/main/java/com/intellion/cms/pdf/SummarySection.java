package com.intellion.cms.pdf;

import java.util.List;

public class SummarySection {

	private String title;
	
	private List<PropertyInfo> values;
	
	private List<TableData> tableDataList;

	private List<SummarySection> subSection;
	
	private String visible;
	public String getTitle() {
		return title;
	}

	public List<PropertyInfo> getSectionValues() {
		return values;
	}

	public List<SummarySection> getSubSection() {
		return subSection;
	}

	public List<PropertyInfo> getValues() {
		return values;
	}

	public void setValues(List<PropertyInfo> values) {
		this.values = values;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubSection(List<SummarySection> subSection) {
		this.subSection = subSection;
	}

	public List<TableData> getTableDataList() {
		return tableDataList;
	}

	public void setTableDataList(List<TableData> tableDataList) {
		this.tableDataList = tableDataList;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	
}
