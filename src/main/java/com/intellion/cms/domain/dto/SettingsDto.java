package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;


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
		List<SettingsParamsDto> settingsParamsDtoList = new ArrayList<SettingsParamsDto>();
		List<SettingsParams> settingsParamsList = settings.getSettingsParams();

		if(null != settingsParamsList && settingsParamsList.size() > 0){
			
			for(SettingsParams settingsParams:settingsParamsList){
				SettingsParamsDto settingsParamsDto = new SettingsParamsDto(settingsParams);
				settingsParamsDtoList.add(settingsParamsDto);
			}
		}		
		
		setSettingsParamsDtos(settingsParamsDtoList);
	}
	
	public static Settings Dto2Pojo(SettingsDto settingsDto){
		
		Settings settings = new Settings();
		List<SettingsParams> settingsParamsList = new ArrayList<SettingsParams>();
		
		settings.setId(settingsDto.getId());
		settings.setType(settingsDto.getType());
		settings.setCategory(settingsDto.getCategory());
		List<SettingsParamsDto> settingsParamsDtos = settingsDto.getSettingsParamsDtos();
		if(null != settingsParamsDtos && settingsParamsDtos.size() > 0){
			
			for(SettingsParamsDto settingsParamsDto:settingsParamsDtos){
				SettingsParams settingsParams = SettingsParamsDto.Dto2Pojo(settingsParamsDto);
				settingsParamsList.add(settingsParams);
			}
		}
		settings.setSettingsParams(settingsParamsList);
		return settings;
	}

}