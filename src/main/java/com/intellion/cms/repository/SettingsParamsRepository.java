package com.intellion.cms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.SettingsParams;

@Repository("settingsParamsRepository")
public interface SettingsParamsRepository extends CrudRepository<SettingsParams, Long> {

}
