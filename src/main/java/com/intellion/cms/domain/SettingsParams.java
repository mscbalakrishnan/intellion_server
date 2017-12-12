package com.intellion.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

import lombok.Data;
@Entity
public class SettingsParams extends EntityWithSurrogatePK {
	@Column(nullable = false)
	private String paramName;
	
	@Column(nullable = false)
	private String paramValue;

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

	@Override
	public String toString() {
		return "SettingsParams [paramName=" + paramName + ", paramValue=" + paramValue + "]";
	}
	
}
