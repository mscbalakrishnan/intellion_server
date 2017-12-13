package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.Set;

import com.intellion.cms.domain.Address;
import com.intellion.cms.domain.Category;
import com.intellion.cms.domain.SettingsParams;

public class SettingsParamsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String paramName;
	private String paramValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	public SettingsParamsDto() {}
	
	public SettingsParamsDto(SettingsParams settingsParams) {
		setId(settingsParams.getId());
		setParamName(settingsParams.getParamName());
		setParamValue(settingsParams.getParamValue());
	}
	
	public static SettingsParams Dto2Pojo(SettingsParamsDto settingsParamsDto){
		SettingsParams pojo = new SettingsParams();
		pojo.setParamName(settingsParamsDto.getParamName());
		pojo.setParamValue(settingsParamsDto.getParamValue());
		return pojo;
	}	
	

}