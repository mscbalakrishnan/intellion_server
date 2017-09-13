package com.example.demo.service;

import com.example.demo.domain.Appointment;

public interface NotifyService {
	public String getWelcomeMessage(String template, String name);
	public String getMsgForAppConfirm(String template, Appointment appointment);
	public String getMsgForAppConfirmForDoc(String template, Appointment appointment);
	public void sendSMS(String phoneNumber, String text);
}
