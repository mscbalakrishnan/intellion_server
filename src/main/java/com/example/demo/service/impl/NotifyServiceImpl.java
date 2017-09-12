package com.example.demo.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.annotation.PostConstruct;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.template.App_Confirm;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.NotifyService;

@Component("notifyService")
public class NotifyServiceImpl implements NotifyService {
	private static final Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);
	private String urlStr = "http://bhashsms.com/api/sendmsg.php";
	private String priority = "ndnd";
	private String type = "normal";
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Value("${sms.enable}")
	private String enableSMS;
	
	@Value("${clinic.name}")
	private String hospital;
	
	@Value("${clinic.mobile}")
	private String mobile;
	
	@Value("${clinic.address}")
	private String address;
	
	@Value("${sms.user}")
	private String smsUser;
	
	@Value("${sms.password}")
	private String smsPassword;
	
	@Value("${sms.sender}")
	private String smsSender;
	
	@PostConstruct
    public void init() {
        logger.debug("clinic.hospital : {}",hospital);
        logger.debug("clinic.address : {}",address);
        logger.debug("clinic.mobile : {}",mobile);
        logger.debug("sms.user : {}",smsUser);
        logger.debug("sms.password : {}",smsPassword);
        logger.debug("sms.sender : {}",smsSender);
    }
	
	@Override
	public String getWelcomeMessage(String template, String name) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		Template t = ve.getTemplate(template);
		VelocityContext context = new VelocityContext();
		
		Appointment appointment = appointmentService.findOne(1);
		App_Confirm app_Confirm = new App_Confirm();
		app_Confirm.setHospital(hospital);
		app_Confirm.setUserName(appointment.getPatient().getName());
		app_Confirm.setDateTime(appointment.getTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)));
		
		context.put("data", app_Confirm);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
	@Override
	public String getMsgForAppConfirm(String template, Appointment appointment) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		App_Confirm app_Confirm = new App_Confirm();
		app_Confirm.setHospital(hospital);
		app_Confirm.setUserName(appointment.getPatient().getName());
		app_Confirm.setDateTime(appointment.getTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)));
		
		VelocityContext context = new VelocityContext();
		context.put("data", app_Confirm);
		
		Template t = ve.getTemplate(template);
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
	@Override
	public void sendSMS(String phoneNumber, String text){
//		phoneNumber shoule be 10 digit Number.class validation should be doen in UI
		urlStr += "?user=" + smsUser;
		urlStr += "&pass=" + smsPassword;
		urlStr += "&sender=" + smsSender;
		urlStr += "&phone=" + phoneNumber;
		try {
			urlStr += "&text=" + URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("Unable to encode the sms text message.", e1);
		}
		urlStr += "&priority=" + priority;
		urlStr += "&stype=" + type;
		logger.debug("urlStr : {}",urlStr);
		
		if (!Boolean.parseBoolean(enableSMS)){
			logger.debug("SMS DISABLED !!! ");
			return;
		}
		try {
			URL url = new URL(urlStr);
			URLConnection urlConnection = url.openConnection();
	        HttpURLConnection connection = null;
	        if(urlConnection instanceof HttpURLConnection) {
	        	connection = (HttpURLConnection) urlConnection;
	        }
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String urlString = "";
	        String current;
	        while((current = in.readLine()) != null) {
	        	urlString += current;
	        }
	        logger.debug("output of sms is {}", urlString);

		} catch (IOException  e) {
			logger.error("Unable to send sms", e);
		}
	}
}
