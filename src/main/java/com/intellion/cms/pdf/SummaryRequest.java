package com.intellion.cms.pdf;

import java.util.List;

public class SummaryRequest {

	private String title;
	
	private String subTitle;
	
	private List<SummarySection> sectionList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SummarySection> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<SummarySection> sectionList) {
		this.sectionList = sectionList;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	
}