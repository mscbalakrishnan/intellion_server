package com.intellion.cms.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;
import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;
import com.intellion.cms.repository.SmsDetailsRepository;
import com.intellion.cms.service.AppointmentService;
import com.intellion.cms.service.NotifyService;
import com.intellion.cms.service.PatientService;
import com.intellion.cms.service.SettingsService;
import com.intellion.cms.service.SmsDetailsService;
import com.intellion.cms.util.SmsContentUtil;

@Component("notifyService")
public class NotifyServiceImpl implements NotifyService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private PatientService patientService;
	@Autowired
    private AppointmentService appointmentService;
	@Autowired
    private SmsDetailsService smsDetailsService;
	@Autowired
	private SmsDetailsRepository smsDetailsRepository;
	@Autowired
    private SettingsService settingsService;
	public Properties getSmsParams() {
		Properties properties = new Properties();
		for (Settings settings : settingsService.findByCategory("sms")) {
			for (SettingsParams params : settings.getSettingsParams()) {
				properties.setProperty(params.getParamName(), params.getParamValue());
			}
		}
		logger.debug("properties: {}", properties);
		return properties;
	}
	
//	second (0-59) / minute (0-59) / hour (0-23) / day of month (1-31) / month (1-12) / day of week (0-6)
//	Testing every twenty  seconds...
	@Scheduled(cron="*/50 * * * * *")
	//@Scheduled(cron="0 0 9-18 * * *")
	@Override
	public void sendSMS(){
		logger.debug("sendSMS() called...");
		Properties properties =  getSmsParams();
		if (!Boolean.parseBoolean(properties.getProperty("sms_global_switch"))){
			logger.debug("SMS DISABLED !!! ");
			return;
		}
		String msgCode = "";
		for (SmsDetails smsDetails:smsDetailsService.getPendingSms()) {
			
			String urlStr = properties.getProperty("url");
			urlStr += "?user=" + properties.getProperty("username");
			urlStr += "&pass=" + properties.getProperty("password");
			urlStr += "&sender=" + properties.getProperty("sender");
			urlStr += "&phone=" + smsDetails.getContactList();
			try {
				urlStr += "&text=" + URLEncoder.encode(smsDetails.getDetail(), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				logger.error("Unable to encode the sms text message.", e1);
			}
			urlStr += "&priority=" + properties.getProperty("priority");
			urlStr += "&stype=" + properties.getProperty("type");
			logger.debug("urlStr for SMS : {}",urlStr);
			String urlString = "";
			try {
				URL url = new URL(urlStr);
				URLConnection urlConnection = url.openConnection();
		        HttpURLConnection connection = null;
		        if(urlConnection instanceof HttpURLConnection) {
		        	connection = (HttpURLConnection) urlConnection;
		        }
		        
		        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		        
		        String current;
		        while((current = in.readLine()) != null) {
		        	urlString += current;
		        }
		        
		        if(urlString.length() > 0){
		        	
		        	urlString = urlString.toUpperCase();
		        	if(urlString.startsWith("S.")){
	    				String[] codeToken = urlString.split(" ");
	    				if(null != codeToken && codeToken.length > 0){
	    					msgCode = codeToken[0];
	    				}
		    			smsDetails.setStatus(SmsStatus.SUCCESS.name());
		    			smsDetails.setRetryCount(0);
		    			
		        	}else{
		    			smsDetails.setStatus(SmsStatus.FAILURE.name());
		    			smsDetails.setRetryCount(smsDetails.getRetryCount()-1);
		        	}
		        	
		        }else{
	    			smsDetails.setStatus(SmsStatus.FAILURE.name());
	    			smsDetails.setRetryCount(smsDetails.getRetryCount()-1);
	        	}
		        
		        logger.debug("output of sms is {}", urlString);
		        smsDetails.setResponseString(urlString);
				smsDetailsService.save(smsDetails);
			} catch (Exception  e) {
				logger.error("Unable to send sms", e);
				smsDetails.setStatus(SmsStatus.FAILURE.name());
				smsDetails.setRetryCount(smsDetails.getRetryCount()-1);
				smsDetails.setResponseString(urlString);
				smsDetailsService.save(smsDetails);
			}
		}
	}
	
	

	//@Scheduled(cron="*/45 * * * * *")
	@Scheduled(cron="0 0 9-18 * * *")
	@Override
	public void patientBirthDateCheckScheduler(){
		
		logger.debug("patientBirthDateCheckScheduler() called...");
		Properties properties =  getSmsParams();
		if (!Boolean.parseBoolean(properties.getProperty("sms_birthday_alarm"))){
			logger.debug("BIRTHDAY ALARM DISABLED !!! ");
			return;
		}
		LocalDate currLocalDate = LocalDate.now();
		List<Patient> patList = patientService.findByDOB(currLocalDate);
		
		for (Patient patient:patientService.findByDOB(currLocalDate)) {
			
			String patientPhoneNumber = patient.getMobileNumber1();
			if(patientPhoneNumber !=null && !patientPhoneNumber.trim().isEmpty()){
				String bdayWishMsg = SmsContentUtil.getInstance().getBirthDayWishMessage("birthdaywish.vm", patient.getName());
				logger.debug("*********** PATIENT BIRTHDAY SMS CONTENT: "+bdayWishMsg);
				SmsDetails smsDetails = new SmsDetails();
				smsDetails.setContactList(patientPhoneNumber);
				smsDetails.setDetail(bdayWishMsg);
				smsDetails.setRetryCount(3);
				smsDetails.setDate(new Date().getTime());
				smsDetails.setName(SmsContentUtil.SMS_BDAY_NAME_PREFIX + patient.getId());
				smsDetails.setStatus(SmsStatus.PENDING.name());
				smsDetailsRepository.save(smsDetails);
			}
			
		}
	}	
	

	//@Scheduled(cron="*/45 * * * * *")
	@Scheduled(cron="0 0 9-18 * * *")
	@Override
	public void periodicReminderAppointment(){
		logger.debug("periodicReminderAppointment() called...");
		Properties properties =  getSmsParams();
		LocalDate currLocalDate = LocalDate.now();
		if (!Boolean.parseBoolean(properties.getProperty("sms_periodic_reminder"))){
			logger.debug("PERIODIC REMINDER SMS DISABLED !!! ");
			return;
		}
		
		String periodicInterval = properties.getProperty("sms_periodic_reminder_Days");
		int periodicIntervalInnum = Integer.parseInt(periodicInterval);
		
		LocalDate currLocalDateWithInterval = currLocalDate.plusDays(periodicIntervalInnum);
		Iterable<Appointment> appointmentList = appointmentService.findByTimeBetween(currLocalDateWithInterval,currLocalDateWithInterval);
		
		for (Appointment appointment:appointmentList) {
			
			Patient patient = appointment.getPatient();
			String patientPhoneNumber = patient.getMobileNumber1();
			
			if(patientPhoneNumber !=null && !patientPhoneNumber.trim().isEmpty()){
				String periodicRemainderMsg = SmsContentUtil.getInstance().getAppointmentReminderMessage("appointment_reminder.vm", patient.getName(),currLocalDateWithInterval.toString());
				logger.debug("*********** APPOINTMENT PERIODIC REMINDER SMS CONTENT: "+periodicRemainderMsg);
				SmsDetails smsDetails = new SmsDetails();
				smsDetails.setContactList(patientPhoneNumber);
				smsDetails.setDetail(periodicRemainderMsg);
				smsDetails.setRetryCount(3);
				smsDetails.setDate(new Date().getTime());
				smsDetails.setName(SmsContentUtil.SMS_APPOINTMENT_REMINDER_NAME_PREFIX + patient.getId());
				smsDetails.setStatus(SmsStatus.PENDING.name());
				smsDetailsRepository.save(smsDetails);
			}
			
		}
	}		
	
	
}
