package com.intellion.cms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping(value="/intelhosp/home")
public class CmsController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
		
//	@Value("${application.message:Hello World}")
//	private String message = "Hello World";
//	
//	@GetMapping
//	public String welcome(Model model) {
//		model.addAttribute("name", "Kumaraguru");
//		model.addAttribute("time", new Date());
//		model.addAttribute("message", this.message);
//		return "welcome";
//	}
}
