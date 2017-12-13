package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.intellion.cms.domain.Settings;


public class SettingsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String type;
	private String category;
	private List<SettingsParamsDto> settingsParamsDtos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<SettingsParamsDto> getSettingsParamsDtos() {
		return settingsParamsDtos;
	}
	public void setSettingsParamsDtos(List<SettingsParamsDto> settingsParamsDtos) {
		this.settingsParamsDtos = settingsParamsDtos;
	}
	
	
	
	public SettingsDto(String type, String category, List<SettingsParamsDto> settingsParamsDtos) {
		super();
		this.type = type;
		this.category = category;
		this.settingsParamsDtos = settingsParamsDtos;
	}
	
	public SettingsDto(Settings settings) {
		
		setId(settings.getId());
		setType(settings.getType());
		setCategory(settings.getCategory());
		
	}
	

}