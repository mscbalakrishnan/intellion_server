package com.example.demo.service.impl;

import java.io.StringWriter;
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
import com.example.demo.web.NotifyController;

@Component("notifyService")
public class NotifyServiceImpl implements NotifyService {
	private static final Logger logger = LoggerFactory.getLogger(NotifyController.class);
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Value("${clinic.name}")
	private String hospital;
	
	@Value("${clinic.mobile}")
	private String mobile;
	
	@Value("${clinic.address}")
	private String address;
	
	@PostConstruct
    public void init() {
        logger.debug("clinic.hospital : {}",hospital);
        logger.debug("clinic.address : {}",address);
        logger.debug("clinic.mobile : {}",mobile);
    }
	
	@Override
	public String getWelcomeMessage(String name) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		Template t = ve.getTemplate("app_confirm.vm");
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

}
