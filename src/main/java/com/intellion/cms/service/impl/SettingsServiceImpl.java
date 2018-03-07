package com.intellion.cms.service.impl;

import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.Settings;
import com.intellion.cms.repository.SettingsRepository;
import com.intellion.cms.service.SettingsService;
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
		Settings s = null;
		try {
			s = settingsRepository.save(settings);
		} catch (Exception e1) {
			Exception e = (Exception)e1.getCause();
			e = (Exception)e.getCause();
			if (e instanceof ConstraintViolationException){
				ConstraintViolationException e2 = (ConstraintViolationException) e;
				String errMsg = "";
				for (ConstraintViolation cv : e2.getConstraintViolations()){
					errMsg += cv.getMessageTemplate();
				}
				throw new IllegalArgumentException(errMsg);
			} else {
				throw e1;
			}
			
		}
		logger.debug("Saved Object is {}",s);
		return s;
	}

	@Override
	public Iterable<Settings> findAll() {
		return settingsRepository.findAll();
	}

	@Override
	public Settings findOne(long id) {
		return settingsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public void delete(long id) {
		settingsRepository.deleteById(id);
	}

	@Override
	public void delete(Settings settings) {
		settingsRepository.delete(settings);
	}

	@Override
	public Iterable<Settings> findByCategory(String catName) {
		return settingsRepository.findByCategory(catName);
	
	}

	@Override
	public Settings getSettings(String type, String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
