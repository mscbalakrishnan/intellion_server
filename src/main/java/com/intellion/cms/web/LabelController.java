package com.intellion.cms.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.Label;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;
import com.intellion.cms.domain.dto.AppointmentDto;
import com.intellion.cms.domain.dto.LabelDto;
import com.intellion.cms.domain.dto.PatientDto;
import com.intellion.cms.repository.SmsDetailsRepository;
import com.intellion.cms.service.LabelService;
import com.intellion.cms.service.PatientService;
import com.intellion.cms.util.DateUtil;
import com.intellion.cms.util.SmsContentUtil;


@RestController
@RequestMapping(value = "/intelhosp/label")
public class LabelController {
	private static final Logger logger = LoggerFactory.getLogger(LabelController.class);
	@Autowired
	private LabelService labelService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private SmsDetailsRepository smsDetailsRepository;
	
	
	/**
	 * @param request
	 * @return
	 */
	@GetMapping(value="/labeldto")
	@ResponseBody
	public List<LabelDto> getAllLabels(HttpServletRequest request) {
		System.out.println("Muralibabu: getAllLabels");
		List<Label> labels =  (List<Label>) this.labelService.findAll();
		List<LabelDto> toReturn = new ArrayList<>();
		labels.forEach(l->toReturn.add(new LabelDto(l)));
		return toReturn;
	}
	
	
	@PostMapping
	@ResponseBody
	public LabelDto addLabel(@RequestBody LabelDto labelDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" );
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
		logger.debug("*********** Received the Object to EDIT {}" );
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
	
	
	@GetMapping(value="/grouppromo")
	public String findPatIDsByGroupId(@RequestParam  long labelId, @RequestParam  String promoMsg) {
		logger.debug("group Id ----> {}",labelId);
		Label label = this.labelService.findOne(labelId);
		List<Patient> patList = label.getPatientList(); 
		for(Patient patient:patList){
			if(null != patient){
				sendSmsToPatient(patient.getMobileNumber1(), patient.getId(), promoMsg, "PROMO");
			}
		}
		
		return "success";
	}
	
	private void sendSmsToPatient(String patientPhoneNumber, String patientId, String promoMsg, String smsIdentifier) {
		
		logger.debug("*********** PROMO SMS CONTENT FOR PATIENT:"+promoMsg);
		if (patientPhoneNumber != null && patientPhoneNumber.trim().length() != 0) {
			SmsDetails smsDetails = new SmsDetails();
			smsDetails.setContactList(patientPhoneNumber);
			smsDetails.setDetail(promoMsg);
			smsDetails.setRetryCount(5);
			smsDetails.setDate(new Date().getTime());
			smsDetails.setName(smsIdentifier + patientId);
			smsDetails.setStatus(SmsStatus.PENDING.name());
			smsDetailsRepository.save(smsDetails);
		}
	}	
	
	
}
