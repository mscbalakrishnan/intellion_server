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
@Data
public class Settings extends EntityWithSurrogatePK{
	private String type;
	private String category;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name="settings_id")
	private List<SettingsParams> settingsParams = new ArrayList<>();
}
