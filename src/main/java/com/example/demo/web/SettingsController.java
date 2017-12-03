package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Settings;
import com.example.demo.service.SettingsService;

@RestController
@RequestMapping(value = "/intelhosp/settings")
public class SettingsController {
	private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);
	@Autowired
	private SettingsService settingsService;

	@GetMapping
	@ResponseBody
	public List<Settings> getAllSettings(HttpServletRequest request) {
		List<Settings> toReturn = new ArrayList<>();
		List<Settings> settings = (List<Settings>) this.settingsService.findAll();
		return settings;
	}
}
