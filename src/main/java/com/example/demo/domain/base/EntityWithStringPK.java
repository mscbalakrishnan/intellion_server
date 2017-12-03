package com.example.demo.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public abstract class EntityWithStringPK extends BaseEntity {
    private static final long serialVersionUID = 960073121019003252L;
    //private static Logger logger = Log.getLogger(BaseEntity.class);
    
	@Id
	@Column(name = "name", unique = true, 
		nullable = false, length = 45)
	private String name;
    
	public Serializable getPrimaryKey() { 
		return getName();
	}
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}