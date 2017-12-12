package com.intellion.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

import lombok.Data;
@Entity
@Data
public class SettingsParams extends EntityWithSurrogatePK {
	@Column(nullable = false)
	private String paramName;
	
	@Column(nullable = false)
	private String paramValue;
}
