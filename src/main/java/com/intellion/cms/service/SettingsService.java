package com.intellion.cms.service;

import java.util.List;

import com.intellion.cms.domain.Settings;

public interface SettingsService {
	Settings getSettings(String type, String category);
	Settings getSettings(String category);
	Settings save(Settings settings);

	Iterable<Settings> findAll();

	Settings findOne(long id);

	void delete(long id);

	void delete(Settings settings);

	Iterable<Settings> findByCategory(String catName);
	
}
