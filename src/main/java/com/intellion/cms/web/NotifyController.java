package com.intellion.cms.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intellion.cms.service.NotifyService;

@Controller
@RequestMapping(value = "/intelhosp/notify")
public class NotifyController {
	private static final Logger logger = LoggerFactory.getLogger(NotifyController.class);
	@Autowired
	private NotifyService notifyService;

	@GetMapping(value = "/welcome")
	@ResponseBody
	public String getWelcomeMessage(HttpServletRequest request) {
//		return notifyService.getWelcomeMessage("app_confirm.vm","Kumaraguru");
		return null;
	}

}
