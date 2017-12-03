package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.example.demo.domain.base.EntityWithSurrogatePK;
@Entity
public class Settings extends EntityWithSurrogatePK{

	private String type;
	private String category;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name="settings_id")
	private List<SettingsParams> settingsParams = new ArrayList<>();

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
		return "Settings [type=" + type + ", category=" + category + ", settingsParams=" + settingsParams + "]";
	}

	

}
