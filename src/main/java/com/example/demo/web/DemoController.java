package com.example.demo.web;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping(value="/intelhosp/home")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
		
	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	
	@GetMapping
	public String welcome(Model model) {
		model.addAttribute("name", "Kumaraguru");
		model.addAttribute("time", new Date());
		model.addAttribute("message", this.message);
		return "welcome";
	}
}
