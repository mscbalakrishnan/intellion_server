package com.intellion.cms.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.intellion.cms.domain.Medication;
import com.intellion.cms.domain.Prescription;
import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.dto.MedicationDto;
import com.intellion.cms.domain.dto.PrescriptionDto;
import com.intellion.cms.domain.dto.SettingsDto;
import com.intellion.cms.domain.dto.SettingsParamsDto;
import com.intellion.cms.service.SettingsService;


@RestController
@RequestMapping(value = "/intelhosp/settings")
public class SettingsController {
	private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);
	@Autowired
	private SettingsService settingsService;

	@GetMapping
	@ResponseBody
	public List<SettingsDto> getAllSettings(HttpServletRequest request) {
		logger.debug("getAllSettings ------------------ ");
		List<Settings> settingsList = (List<Settings>) this.settingsService.findAll();
		List<SettingsDto> toReturn = new ArrayList<>();
		settingsList.forEach(s->toReturn.add(new SettingsDto(s)));
		logger.debug("toReturn -->"+toReturn);
		return toReturn;
	}
	
	@PutMapping
	@ResponseBody
	public SettingsDto editSettings(@RequestBody SettingsDto settingsDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , settingsDto.toString());
		Settings existSettings = this.settingsService.findOne(settingsDto.getId());
		existSettings = SettingsDto.Dto2Pojo(existSettings, settingsDto);
		existSettings = this.settingsService.save(existSettings);
		logger.debug("*********** After set the value from UI {}" , existSettings);
		return new SettingsDto(existSettings);
	}	
	
	
	@GetMapping(value="/category/{catid}")
	public List<SettingsDto> findByCategory(@PathVariable("catid") String catId, HttpServletRequest request) {
		logger.debug("Category ID ----> {}",catId);
		List<Settings> settingsListByCat =  (List<Settings>)this.settingsService.findByCategory("SMS");
		List<SettingsDto> toReturn = new ArrayList<>();
		settingsListByCat.forEach(s->toReturn.add(new SettingsDto(s)));
		return toReturn;
	}	
	
	
}
