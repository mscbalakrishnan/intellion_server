package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.intellion.cms.domain.Label;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;



public class SmsGroupDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String smsContent;
	private String smsType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	
	
}
