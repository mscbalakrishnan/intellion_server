package com.intellion.cms.service;

public interface NotifyService {
//	public String getWelcomeMessage(String template, String name);
//	public String getMsgForAppConfirm(String template, Appointment appointment);
//	public String getMsgForAppConfirmForDoc(String template, Appointment appointment);
	public void sendSMS();
	public void patientBirthDateCheckScheduler();
//	public String getMsgForApp(String template, Appointment appointment, String operation);
//	public String getMsgForDelApp(String template, AppointmentDto appointmentDto, String toUser);
}
