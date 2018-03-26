package com.intellion.cms.domain;

import javax.persistence.Entity;

import com.intellion.cms.domain.base.EntityWithSurrogatePK;

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
	private String responseString;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getFailureCause() {
		return failureCause;
	}
	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getContactList() {
		return contactList;
	}
	public void setContactList(String contactList) {
		this.contactList = contactList;
	}
	public String getResponseString() {
		return responseString;
	}
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	
	
	
}
