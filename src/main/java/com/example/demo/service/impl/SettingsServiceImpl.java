package com.example.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Settings;
import com.example.demo.repository.SettingsRepository;
import com.example.demo.service.SettingsService;
@Service("settingsService")
public class SettingsServiceImpl implements SettingsService {
	private static final Logger logger = LoggerFactory.getLogger(SettingsServiceImpl.class);
	private final SettingsRepository settingsRepository;

    @Autowired
    public SettingsServiceImpl(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }
    
	@Override
	public Settings getSettings(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Settings save(Settings settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Settings> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Settings findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Settings settings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Settings> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
