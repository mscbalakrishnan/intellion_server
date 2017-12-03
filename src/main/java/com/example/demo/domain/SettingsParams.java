package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
@Entity
public class SettingsParams {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
//	@SequenceGenerator(name = "settingsparams_generator", sequenceName="settings_params_sequence", initialValue = 23)
//	@GeneratedValue(generator = "settingsparams_generator")
	private Long id;

	@Column(nullable = false)
	private String paramName;
	
	@Column(nullable = false)
	private String paramValue;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "settings_id")
//	private Settings settings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

//	public Settings getSettings() {
//		return settings;
//	}
//
//	public void setSettings(Settings settings) {
//		this.settings = settings;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SettingsParams [id=" + id + ", paramName=" + paramName + ", paramValue=" + paramValue + "]";
	}

//	@Override
//	public String toString() {
//		return "SettingsParams [id=" + id + ", paramName=" + paramName + ", paramValue=" + paramValue + ", settings="
//				+ settings + "]";
//	}

	
}
