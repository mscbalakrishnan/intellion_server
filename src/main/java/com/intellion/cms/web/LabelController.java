package com.intellion.cms.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.intellion.cms.domain.Label;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;
import com.intellion.cms.domain.dto.LabelDto;
import com.intellion.cms.repository.SmsDetailsRepository;
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
	@Autowired
	private SmsDetailsRepository smsDetailsRepository;
	
	
	/**
	 * @param request
	 * @return
	 */
	@GetMapping()
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
		logger.debug("*********** Received the Object to ADD {}",labelDto );
		Label label = new Label();
		label.setLabelname(labelDto.getName());
		List<Patient> patients = new ArrayList<>();
		for (String patientId: labelDto.getPatientIdList()){
			Patient patient = patientService.findOne(patientId);
			if (label.getPatientList() == null){
				label.setPatientList(new HashSet<Patient>());
			}
			label.getPatientList().add(patient);
			patients.add(patient);
		}
		label = labelService.save(label);
		for (Patient patient: patients){
			patient.getLabels().add(label);
			patientService.save(patient);
		}
		label = labelService.findOne(label.getId());
		return new LabelDto(label);
	}	
	@PutMapping
	@ResponseBody
	public LabelDto editLabel(@RequestBody LabelDto labelDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}", labelDto );
		Label label = labelService.findOne(labelDto.getId());
		Set<Patient> old_patients = label.getPatientList();
		Set<String> old_patient_ids = old_patients.stream().map(Patient -> Patient.getId()).collect(Collectors.toSet());
		label.setLabelname(labelDto.getName());
		Set<Patient> patients = new HashSet<Patient>();
		for (String patientId: labelDto.getPatientIdList()){
			patients.add(patientService.findOne(patientId));
		}
		Set<String> new_patient_ids = patients.stream().map(Patient -> Patient.getId()).collect(Collectors.toSet());
		label.setPatientList(patients);
		label = labelService.save(label);
		for (Iterator<String> i = old_patient_ids.iterator();i.hasNext();){
			String patientId = i.next();
			if (!new_patient_ids.contains(patientId)){
				Patient patient = patientService.findOne(patientId);
				patient.getLabels().remove(label);
				patient = patientService.save(patient);
			}
		}
		for (Iterator<String> i = new_patient_ids.iterator();i.hasNext();){
			String patientId = i.next();
			if (!old_patient_ids.contains(patientId)){
				Patient patient = patientService.findOne(patientId);
				patient.getLabels().add(label);
				patient = patientService.save(patient);
			}
		}
		label = labelService.findOne(label.getId());
		return new LabelDto(label);
	}		
	
	@DeleteMapping(value="/{labelid}")
	@ResponseBody
	public void deleteLabel(@PathVariable("labelid") long labelId, HttpServletRequest request) {
		Label label = labelService.findOne(labelId);
		for (Patient patient:label.getPatientList()){
			patient.getLabels().remove(label);
			patientService.save(patient);
		}
		labelService.delete(labelId);
	}
	
	
	@GetMapping(value="/grouppromo")
	public String findPatIDsByGroupId(@RequestParam  long labelId, @RequestParam  String promoMsg) {
		logger.debug("group Id ----> {}",labelId);
		Label label = this.labelService.findOne(labelId);
/*		List<Patient> patList = label.getPatientList(); 
		for(Patient patient:patList){
			if(null != patient){
				sendSmsToPatient(patient.getMobileNumber1(), patient.getId(), promoMsg, "PROMO");
			}
		}*/
		
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
