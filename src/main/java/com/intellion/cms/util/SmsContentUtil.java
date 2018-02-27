package com.intellion.cms.util;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;
import com.intellion.cms.domain.template.SmsTemplateData;
import com.intellion.cms.service.SettingsService;

public class SmsContentUtil {
    private SettingsService settingsService;
	private static final SmsContentUtil smsContentUtil = new SmsContentUtil();
	private static final Logger logger = LoggerFactory.getLogger(SmsContentUtil.class);
	VelocityEngine ve = null;
	VelocityContext context = null;
	private final String BASE_DIR = "sms_templates";
	private static final String BOOSTERDATE = "Your booster is scheduled on ";
	private static final String APPOINTMENT = "Please call 9876543211 to book your appointment";
	private static final String GREETINGS = "Gretings from XYZ!!\n";
	private static final String COMMA = ",\n";
	private static final String EOL = "\n";
	private static final String DEAR = "Dear ";
	private static final String ID = "ID: ";
	
	private static final String DUE_DATE = "Due date:";
	private static final String DUE_AMOUNT = "Amount Due: ";
	private static final String PATICULARS = "Paticulars: ";
	private static final String ENSURE_TEXT = "Kindly ensure you have paid before due date";
	
	private static final String IGNORE = "Please ignore if you already have taken";
	private static final String IGNOREPAID = "Please ignore if you have already paid";
	public static final String SMS_REG_NAME_PREFIX = "REGISTRATION::";
	public static final String SMS_BDAY_NAME_PREFIX = "BIRTHDAY::";
	public static final String SMS_APPOINTMENT_NAME_PREFIX = "APPOINTMENT::";
	public static final String SMS_APPOINTMENT_RESCHEDULE_NAME_PREFIX = "APPOINTMENT RESCHEDULE::";
	public static final String SMS_APPOINTMENT_CANCEL_NAME_PREFIX = "APPOINTMENT CANCEL::";
	public static final String SMS_APPOINTMENT_REMINDER_NAME_PREFIX = "APPOINTMENT REMINDER::";
	
	private SmsContentUtil() {
		context = new VelocityContext();
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		settingsService = SpringContextBridge.services().getSettingsService();
	}
	public static SmsContentUtil getInstance(){
		return smsContentUtil;
	}
	public static StringBuilder getSmsContentTherapyNotification(String patientName, String patientId, String dueDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(DEAR).append(patientName).append(COMMA).append(GREETINGS);
		sb.append(ID).append(patientId).append(EOL);
		sb.append(BOOSTERDATE).append(dueDate).append(EOL);
		sb.append(APPOINTMENT).append(EOL);
		sb.append(IGNORE).append(EOL);
		return sb;
	}
	
	public static StringBuilder getSmsContentPatientRegistration() {
		StringBuilder sb = new StringBuilder();
		sb.append("welcome");	
		return sb;
	}
	
	public static StringBuilder getSmsContentBilling(String patientName, String patientId, String dueDate,float amount,String paticulars) {
		StringBuilder sb = new StringBuilder();
		sb.append(DEAR).append(patientName).append(COMMA).append(GREETINGS);
		sb.append(ID).append(patientId).append(EOL);
		sb.append(PATICULARS).append(paticulars).append(EOL);
		sb.append(DUE_DATE).append(dueDate).append(EOL);
		sb.append(DUE_AMOUNT).append(amount).append(EOL);
		sb.append(ENSURE_TEXT).append(EOL);
		sb.append(IGNOREPAID).append(EOL);
				
		return sb;
	}

	public Properties getClinicParams() {
		Properties properties = new Properties();
		for (Settings settings : settingsService.findByCategory("clinic")) {
			for (SettingsParams params : settings.getSettingsParams()) {
				properties.setProperty(params.getParamName(), params.getParamValue());
			}
		}
		logger.debug("properties: {}", properties);
		return properties;
	}
	
	public synchronized String getWelcomeMessage(String template, String patientName) {
		Properties properties =  getClinicParams();
		template = BASE_DIR + "/" + template;
		Template t = ve.getTemplate(template);
		
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setPatientName(patientName);
		smsTemplateData.setHospitalName(properties.getProperty("clinicName"));
		smsTemplateData.setHospitalPhone(properties.getProperty("mobile"));
		context.put("data", smsTemplateData);
		
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}

	public synchronized String getBirthDayWishMessage(String template, String patientName) {
		Properties properties =  getClinicParams();
		template = BASE_DIR + "/" + template;
		Template t = ve.getTemplate(template);
		
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setPatientName(patientName);
		smsTemplateData.setHospitalName(properties.getProperty("clinicName"));
		context.put("data", smsTemplateData);
		
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
	
	public synchronized String getAppointmentReminderMessage(String template, String patientName, String dateTime) {
		Properties properties =  getClinicParams();
		template = BASE_DIR + "/" + template;
		Template t = ve.getTemplate(template);
		
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setPatientName(patientName);
		smsTemplateData.setHospitalName(properties.getProperty("clinicName"));
		smsTemplateData.setDateTime(dateTime);
		context.put("data", smsTemplateData);
		
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
	
	
	
	/**
	 * Method will return sms message for patient as well as doctor if the doctorName parameter is not none
	 * @param template
	 * @param patientName
	 * @param doctorName
	 * @param time
	 * @return
	 */
	public String getMsgForSms(String template, String patientName, String doctorName, String time) {
		Properties properties =  getClinicParams();
		template = BASE_DIR + "/" + template;
		Template t = ve.getTemplate(template);
		
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setDateTime(time);
		smsTemplateData.setPatientName(patientName);
		smsTemplateData.setDoctorName(doctorName);
		smsTemplateData.setHospitalName(properties.getProperty("clinicName"));
		smsTemplateData.setHospitalPhone(properties.getProperty("mobile"));
		context.put("data", smsTemplateData);
		
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
}
