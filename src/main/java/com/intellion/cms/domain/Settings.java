package com.intellion.cms.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

import lombok.Data;
@Entity
public class Settings extends EntityWithSurrogatePK{
	private String type;
	private String category;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name="settings_id")
	private List<SettingsParams> settingsParams = new ArrayList<>();

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

	public List<SettingsParams> getSettingsParams() {
		return settingsParams;
	}

	public void setSettingsParams(List<SettingsParams> settingsParams) {
		this.settingsParams = settingsParams;
	}

	@Override
	public String toString() {
		return "Settings [type=" + type + ", category=" + category + ", settingsParams=" + settingsParams + "]";
	}
	
}
