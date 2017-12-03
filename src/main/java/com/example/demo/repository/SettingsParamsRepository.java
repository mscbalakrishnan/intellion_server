package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.SettingsParams;

@Repository("settingsParamsRepository")
public interface SettingsParamsRepository extends CrudRepository<SettingsParams, Long> {

}
