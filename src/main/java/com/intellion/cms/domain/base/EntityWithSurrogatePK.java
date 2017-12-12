package com.intellion.cms.domain.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityWithSurrogatePK extends BaseEntity {
    private static final long serialVersionUID = 960073121019003252L;
    //private static Logger logger = Log.getLogger(BaseEntity.class);
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
	public Serializable getPrimaryKey() { 
		return getId();
	}

    @Override
    public boolean equals(Object obj) {
    	
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EntityWithSurrogatePK other = (EntityWithSurrogatePK) obj;
        if (id != other.id) {
            return false;
        }
                
        return true;
    }
    
    /*@Override
    public int hashCode() {
    	
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (id ^ (id >>> 32));
        
        return result;
    }*/
}