package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;

public class SettingsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String category;
	private List<SettingsParamsDto> settingsParamsDtos;
	public SettingsDto(){}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "SettingsDto [id=" + id + ", category=" + category + ", settingsParamsDtos=" + settingsParamsDtos + "]";
	}
	public SettingsDto(Long id, String category, List<SettingsParamsDto> settingsParamsDtos) {
		super();
		this.id = id;
		this.category = category;
		this.settingsParamsDtos = settingsParamsDtos;
	}
	
	public SettingsDto(Settings settings) {
		
		setId(settings.getId());
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
		//settings.setType(settingsDto.getType());
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
	public static Settings Dto2Pojo(Settings existSettings, SettingsDto settingsDto) {
		if(existSettings.getId().longValue() != settingsDto.getId().longValue()) {
			return null;
		}
		for (SettingsParamsDto sdto:settingsDto.getSettingsParamsDtos()){
			for (SettingsParams spojo:existSettings.getSettingsParams()){
				if (sdto.getId() == spojo.getId()){
					spojo.setParamValue(sdto.getParamValue());
				}
			}
		}
		return existSettings;
	}

	public ClinicDto getClinicObject() {
		ClinicDto clinicDto = new ClinicDto();
		for (SettingsParamsDto dto : settingsParamsDtos) {
			if ("clinicName".equals(dto.getParamName())) {
				clinicDto.setClinicName(dto.getParamValue());
			} else if ("addressLine1".equals(dto.getParamName())) {
				clinicDto.setAddressLine1(dto.getParamValue());
			} else if ("addressLine2".equals(dto.getParamName())) {
				clinicDto.setAddressLine2(dto.getParamValue());
			} else if ("mobile".equals(dto.getParamName())) {
				clinicDto.setMobile(dto.getParamValue());
			} else if ("area".equals(dto.getParamName())) {
				clinicDto.setArea(dto.getParamValue());
			} else if ("city".equals(dto.getParamName())) {
				clinicDto.setCity(dto.getParamValue());
			} else if ("state".equals(dto.getParamName())) {
				clinicDto.setState(dto.getParamValue());
			} else if ("pincode".equals(dto.getParamName())) {
				clinicDto.setPincode(dto.getParamValue());
			} else if ("email".equals(dto.getParamName())) {
				clinicDto.setEmail(dto.getParamValue());
			} else if ("landline".equals(dto.getParamName())) {
				clinicDto.setLandline(dto.getParamValue());
			} else if ("website".equals(dto.getParamName())) {
				clinicDto.setWebsite(dto.getParamValue());
			}
		}
		return clinicDto;
	}

}