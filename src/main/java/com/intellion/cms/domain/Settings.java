package com.intellion.cms.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "category" ))
public class Settings extends EntityWithSurrogatePK{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9111448468913840865L;

	private String category;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name="settings_id")
	private List<SettingsParams> settingsParams = new ArrayList<>();

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
		return "Settings [category=" + category + ", settingsParams=" + settingsParams + "]";
	}
}
