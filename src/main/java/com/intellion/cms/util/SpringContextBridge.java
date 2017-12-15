package com.intellion.cms.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.intellion.cms.service.SettingsService;

@Component
public class SpringContextBridge implements SpringContextBridgedServices, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Autowired
	private SettingsService settingsService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static SpringContextBridgedServices services() {
		return applicationContext.getBean(SpringContextBridgedServices.class);
	}

	@Override
	public SettingsService getSettingsService() {
		return settingsService;
	}
}