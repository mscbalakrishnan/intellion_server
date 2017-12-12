package com.intellion.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

@Entity
public class Role extends EntityWithSurrogatePK{
	
	@Column(name="role")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
