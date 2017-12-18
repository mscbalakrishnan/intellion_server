package com.intellion.cms.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.intellion.cms.domain.Label;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.dto.LabelDto;
import com.intellion.cms.domain.dto.PatientDto;
import com.intellion.cms.service.LabelService;
import com.intellion.cms.service.PatientService;


@RestController
@RequestMapping(value = "/intelhosp/label")
public class LabelController {
	private static final Logger logger = LoggerFactory.getLogger(LabelController.class);
	@Autowired
	private LabelService labelService;
	@Autowired
	private PatientService patientService;
	
	
	/**
	 * @param request
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public List<LabelDto> getAllLabels(HttpServletRequest request) {

		List<Label> labels =  (List<Label>) this.labelService.findAll();
		List<LabelDto> toReturn = new ArrayList<>();
		labels.forEach(l->toReturn.add(new LabelDto(l)));
		return toReturn;
	}
	
	
	@PostMapping
	@ResponseBody
	public LabelDto addLabel(@RequestBody LabelDto labelDto, HttpServletRequest request) {
		Label label = new Label();
		label.setLabelname(labelDto.getName());
		List<String> patIdList = labelDto.getPatientIdList();
		List<Patient> patList = new ArrayList<Patient>();
		if(null != patIdList && patIdList.size() > 0){
			for(String patId:patIdList){
				Patient p = patientService.findOne(patId);
				patList.add(p);
			}
		}
		label.setPatientList(patList);
		label = this.labelService.save(label);
		return new LabelDto(label);
		
	}	
	
	
	@PutMapping
	@ResponseBody
	public LabelDto editLabel(@RequestBody LabelDto labelDto, HttpServletRequest request) {
		Label label = labelService.findOne(labelDto.getId());
		label.setLabelname(labelDto.getName());
		List<String> patIdList = labelDto.getPatientIdList();
		List<Patient> patList = new ArrayList<Patient>();
		if(null != patIdList && patIdList.size() > 0){
			for(String patId:patIdList){
				Patient p = patientService.findOne(patId);
				patList.add(p);
			}
		}
		label.setPatientList(patList);
		label = this.labelService.save(label);
		return new LabelDto(label);
		
	}		
	
	@DeleteMapping(value="/{labelid}")
	@ResponseBody
	public void deleteLabel(@PathVariable("labelid") long labelId, HttpServletRequest request) {
		this.labelService.delete(labelId);
	}
	
	
}
