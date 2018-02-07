package com.intellion.cms.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;
import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;
import com.intellion.cms.service.NotifyService;
import com.intellion.cms.service.SettingsService;
import com.intellion.cms.service.SmsDetailsService;

@Component("notifyService")
public class NotifyServiceImpl implements NotifyService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private SettingsService settingsService;
	@Autowired
    private SmsDetailsService smsDetailsService;
//	private static final Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);
//	private String urlStr = "http://bhashsms.com/api/sendmsg.php";
//	private String priority = "ndnd";
//	private String type = "normal";
	
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
	@Scheduled(cron="*/45 * * * * *")
//	@Scheduled(cron="0 0 9-18 * * *")
	@Override
	public void sendSMS(){
		logger.debug("sendSMS() called...");
		System.out.println("RAVI RAJA sendSMS..:");
		Properties properties =  getSmsParams();
		if (!Boolean.parseBoolean(properties.getProperty("sms_global_switch"))){
			logger.debug("SMS DISABLED !!! ");
			//System.out.println("sendSMS..:DISABLED");
			return;
		}		
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
			
			//System.out.println("sendSMS..:"+urlStr);
			
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
		        smsDetails.setStatus(SmsStatus.SUCCESS.name());
				smsDetailsService.save(smsDetails);
			} catch (IOException  e) {
				logger.error("Unable to send sms", e);
				smsDetails.setRetryCount(smsDetails.getRetryCount()-1);
				smsDetailsService.save(smsDetails);
			}
		}
	}
	
	/*
	@Value("${sms.enable:false}")
	private String enableSMS;
	
	@Value("${clinic.name: not set}")
	private String hospital;
	
	@Value("${clinic.mobile: not set}")
	private String mobile;
	
	@Value("${clinic.address: not set}")
	private String address;
	
	@Value("${sms.user: not set}")
	private String smsUser;
	
	@Value("${sms.password:not set}")
	private String smsPassword;
	
	@Value("${sms.sender:not set}")
	private String smsSender;
	
	@PostConstruct
    public void init() {
        logger.debug("clinic.hospital : {}",hospital);
        logger.debug("clinic.address : {}",address);
        logger.debug("clinic.mobile : {}",mobile);
        logger.debug("sms.user : {}",smsUser);
        logger.debug("sms.password : {}",smsPassword);
        logger.debug("sms.sender : {}",smsSender);
    }*/
}
