package com.intellion.cms.domain.dto;

import java.io.Serializable;

public class SmsData implements Serializable {

	private String content;
	private String contactListString;
	
	public SmsData(String contactNumber, String content) {
		this.contactListString = contactNumber;
		this.content = content;
	}
	
	public String getContactListString() {
		return contactListString;
	}

	public void setContactListString(String contactListString) {
		this.contactListString = contactListString;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}