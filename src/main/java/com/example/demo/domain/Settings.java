package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
@Entity
public class Settings {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
//	@SequenceGenerator(name = "settings_generator",sequenceName="settings_sequence", initialValue = 23)
//	@GeneratedValue(generator = "settings_generator")
	private Long id;

	private String type;
	private String category;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name="settings_id")
	private List<SettingsParams> settingsParams = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SettingsParams> getSettingsParams() {
		return settingsParams;
	}

	public void setSettingsParams(List<SettingsParams> settingsParams) {
		this.settingsParams = settingsParams;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Settings [id=" + id + ", type=" + type + ", category=" + category + ", settingsParams=" + settingsParams
				+ "]";
	}

}
