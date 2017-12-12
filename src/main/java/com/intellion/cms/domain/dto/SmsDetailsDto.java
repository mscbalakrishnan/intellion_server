package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;



public class SmsDetailsDto implements Serializable {

	private Long id;
	private String name;//Scheduler Name
	private String detail;//Sms Content
	private SmsStatus status = SmsStatus.NONE; //SUCCESS/FAILURE
	private String contactList;
	private int retryCount;
	private long date;
	private String failureCause;
	private int statusCode;
	
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
	public SmsStatus getStatus() {
		return status;
	}
	public void setStatus(SmsStatus status) {
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
	
	public String getContactList() {
		return contactList;
	}
	public void setContactList(String contactList) {
		this.contactList = contactList;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public static SmsDetails getEntity(SmsDetailsDto s) {
		SmsDetails t = new SmsDetails();
		t.setDetail(s.getDetail());
		t.setDate(s.getDate());
		t.setFailureCause(s.getFailureCause());
		t.setId(s.getId());
		t.setName(s.getName());
		t.setRetryCount(s.getRetryCount());
		t.setStatus(s.getStatus().name());
		t.setContactList(s.getContactList());
		t.setStatusCode(s.getStatusCode());
		return t;
	}
	
	public static SmsDetailsDto get(SmsDetails s) {
		SmsDetailsDto t = new SmsDetailsDto();
		t.setDetail(s.getDetail());
		t.setDate(s.getDate());
		t.setFailureCause(s.getFailureCause());
		t.setId(s.getId());
		t.setName(s.getName());
		t.setRetryCount(s.getRetryCount());
		t.setStatus(SmsStatus.getByName(s.getStatus()));
		t.setContactList(s.getContactList());
		t.setStatusCode(s.getStatusCode());
		return t;
	}
	
	public static SmsData getSmsData(SmsDetailsDto s) {
		SmsData t = new SmsData(s.getContactList(),s.getDetail());
		
		return t;
	}
	
	
	public static List<SmsDetails> getEntityList(List<SmsDetailsDto> sList){	
		if(sList == null || sList.isEmpty()) {
			return null;
		}
		
		List<SmsDetails> tList = new ArrayList<SmsDetails>();
		for(SmsDetailsDto s : sList) {
			tList.add(getEntity(s));
		}
		
		return tList;
	}
	
	public static List<SmsData> getSmsDataList(List<SmsDetailsDto> sList){	
		if(sList == null || sList.isEmpty()) {
			return null;
		}
		
		List<SmsData> tList = new ArrayList<SmsData>(sList.size());
		for(SmsDetailsDto s : sList) {
			tList.add(getSmsData(s));
		}
		
		return tList;
	}
	
	public static List<SmsDetailsDto> getList(List<SmsDetails> sList){	
		if(sList == null || sList.isEmpty()) {
			return null;
		}
		
		List<SmsDetailsDto> tList = new ArrayList<SmsDetailsDto>();
		for(SmsDetails s : sList) {
			tList.add(get(s));
		}
		
		return tList;
	}
	public String getContact(List<String> contactList) {
		if(contactList == null || contactList.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<contactList.size();i++) {
			String contact = contactList.get(i);
			
			if(i == contactList.size()-1) {
				sb.append(contact);
			} else {
				sb.append(contact).append(",");
			}
		}
		
		return sb.toString();
	}
	
}
