package com.example.demo.domain.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public abstract class BaseEntity implements Entity {
    private static final long serialVersionUID = 960073121019003252L;
    //private static Logger logger = Log.getLogger(BaseEntity.class);
    
    @Version
    @Column(name = "version")
    private int m_version;

    @Column(name = "lastmodifiedtime")
    private long lastModifiedTime;

    @PrePersist
    protected void onCreate() {
    	//logger.trace("onCreate - Entering");
    	
    	Date date = new Date();
        lastModifiedTime = date.getTime();
        
    	//logger.trace("onCreate - Exiting");
    }

    @PreUpdate
    protected void onUpdate() {
    	//logger.trace("onUpdate - Entering");
    	
    	Date date = new Date();
        lastModifiedTime = date.getTime();    
        
    	//logger.trace("onUpdate - Exiting");
    }
    
    /**
     *
     */
    public BaseEntity() {
    	//logger.trace("BaseEntity - Entering");
    	//logger.trace("BaseEntity - Exiting");
    }

    /**
     * @return the lastModifiedTime
     */
	@XmlTransient
    public Date getLastModifiedTime() {
    	//logger.trace("getLastModifiedTime - Entering");
    	
    	Date date = new Date(lastModifiedTime);
    	
        return date;
    }

    /**
     * @param lastModifiedTime
     *            the lastModifiedTime to set
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
    	
        this.lastModifiedTime = lastModifiedTime.getTime();
        
    	//logger.trace("setLastModifiedTime - Exiting");
    }
    
  
    /**
     * @return the version
     */
	@XmlTransient
    public int getVersion() {
    	//logger.trace("getVersion - Entering");
        return m_version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(int version) {
    	
        this.m_version = version;
        
    	//logger.trace("setVersion - Exiting");
    }

    /**
     * @param lastModifiedTime
     *            the lastModifiedTime to set
     */
    public void setLastModifiedTime(long lastModifiedTime) {  	
    	
        this.lastModifiedTime = lastModifiedTime;       
    
    }
}