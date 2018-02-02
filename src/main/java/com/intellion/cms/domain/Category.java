package com.intellion.cms.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.intellion.cms.domain.base.EntityWithSurrogatePK;

@Entity
public class Category extends EntityWithSurrogatePK {

	@Column(nullable = false)
	private String name;

	public Category(String name) {
		this.name = name;
	}
	public Category() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Category [name=" + name + "]";
	}
	
}