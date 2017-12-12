package com.intellion.cms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.Settings;

@Repository("settingsRepository")
public interface SettingsRepository extends CrudRepository<Settings, Long> {

}
