package com.intellion.cms.pdf;

import java.util.List;

public class TableData {

	private List<String> tableHeading;
	
	private List<List<String>> tableData;

	public List<String> getTableHeading() {
		return tableHeading;
	}

	public void setTableHeading(List<String> tableHeading) {
		this.tableHeading = tableHeading;
	}

	public List<List<String>> getTableData() {
		return tableData;
	}

	public void setTableData(List<List<String>> tableData) {
		this.tableData = tableData;
	}
	
}
