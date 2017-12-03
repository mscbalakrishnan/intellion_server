package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Settings;

public interface SettingsService {
	Settings getSettings(String name);

	Settings save(Settings settings);

	Iterable<Settings> findAll();

	Settings findOne(long id);

	void delete(long id);

	void delete(Settings settings);

	List<Settings> findByName(String name);
}
