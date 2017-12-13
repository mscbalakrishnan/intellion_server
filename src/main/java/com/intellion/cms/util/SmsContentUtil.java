package com.intellion.cms.util;

import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.hibernate.validator.internal.util.privilegedactions.GetInstancesFromServiceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.dto.AppointmentDto;
import com.intellion.cms.domain.template.SmsTemplateData;
import com.intellion.cms.service.impl.DoctorServiceImpl;

public class SmsContentUtil {
	
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
	
	private SmsContentUtil() {
		context = new VelocityContext();
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
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
	public synchronized String getWelcomeMessage(String template, String patientName) {
		template = BASE_DIR + "/" + template;
		Template t = ve.getTemplate(template);
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setPatientName(patientName);
		smsTemplateData.setHospitalName("HOSPITALNAME");
		smsTemplateData.setHospitalPhone("HOSPITALPHONE");
		context.put("data", smsTemplateData);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
	/*public String getMsgForAppConfirm(String template, Appointment appointment) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setPatientName(appointment.getPatient().getName());
		smsTemplateData.setHospitalName(hospital);
		smsTemplateData.setDateTime(appointment.getTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)));

		VelocityContext context = new VelocityContext();
		context.put("data", smsTemplateData);
		
		Template t = ve.getTemplate(template);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
	public String getMsgForApp(String template, Appointment appointment, String doctorName) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setDoctorName(doctorName);
		smsTemplateData.setPatientName(appointment.getPatient().getName());
		smsTemplateData.setHospitalName(hospital);
		smsTemplateData.setDateTime(appointment.getTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)));
		
		VelocityContext context = new VelocityContext();
		context.put("data", smsTemplateData);
		
		Template t = ve.getTemplate(template);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
	
	@Override
	public String getMsgForDelApp(String template, AppointmentDto appointmentDto, String patientName) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setDoctorName(appointmentDto.getDoctor().getName());
		smsTemplateData.setPatientName(appointmentDto.getPatient().getName());
		smsTemplateData.setHospitalName(hospital);
		smsTemplateData.setDateTime(appointmentDto.getTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)));
		
		
		VelocityContext context = new VelocityContext();
		context.put("data", smsTemplateData);
		
		Template t = ve.getTemplate(template);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}	
	
	public String getMsgForAppConfirmForDoc(String template, Appointment appointment) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		SmsTemplateData smsTemplateData = new SmsTemplateData();
		smsTemplateData.setDoctorName(appointment.getDoctor().getName());
		smsTemplateData.setPatientName(appointment.getPatient().getName());
		smsTemplateData.setHospitalName(hospital);
		smsTemplateData.setDateTime(appointment.getTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)));
		
		VelocityContext context = new VelocityContext();
		context.put("data", smsTemplateData);
		
		Template t = ve.getTemplate(template);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}*/
}
