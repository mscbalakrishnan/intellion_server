package com.intellion.cms.domain;

import javax.persistence.Entity;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

import lombok.Data;

@Data
@Entity(name="smsdetails")
public class SmsDetails extends EntityWithSurrogatePK {

	private String name;//Scheduler Name
	private String detail;//Sms Content
	private String status; //SUCCESS/FAILURE
	private int retryCount;
	private long date;
	private String failureCause;
	private int statusCode;
	private String contactList;
	
}
