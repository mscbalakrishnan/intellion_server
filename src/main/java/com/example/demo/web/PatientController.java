package com.example.demo.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.Patient;
import com.example.demo.domain.dto.DoctorDto;
import com.example.demo.domain.dto.PatientDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.NotifyService;
import com.example.demo.service.PatientService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping(value="/intelhosp/patients")
public class PatientController {
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private NotifyService notifyService;


	/**
	 * List All Patients
	 * @param request
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public List<PatientDto> getAllPatientsPojo(HttpServletRequest request) {
		List<Patient> patients =  (List<Patient>) this.patientService.findAll();
		List<PatientDto> toReturn = new ArrayList<>();
		patients.forEach(p->toReturn.add(new PatientDto(p)));
		return toReturn;
	}
	
	/**
	 * Get a particular Patient with id 
	 * @param patientId
	 * @param request
	 * @return
	 */
	@GetMapping(value="/{patientid}")
	@ResponseBody
	public PatientDto getPatient(@PathVariable("patientid") long patientId, HttpServletRequest request) {
		return new PatientDto(this.patientService.findOne(patientId));
	}
	@GetMapping(value="/patientname/find")
	@ResponseBody
	public Set<PatientDto> findByName(@RequestParam("name") String name) {
		logger.debug("Patient Name ----> {}",name);
		Set<PatientDto> patientDtos = new HashSet<>();
		List<Patient> patients =  (List<Patient>) this.patientService.findByName(name);
		for (Patient patient:patients) {
			patientDtos.add(new PatientDto(patient));
		}
		return patientDtos;
	}
	/**
	 * Delete Patient
	 * @param patientId
	 * @param request
	 */
	@DeleteMapping(value="/{patientid}")
	@ResponseBody
	public void deletePatient(@PathVariable("patientid") long patientId, HttpServletRequest request) {
		this.patientService.delete(patientId);
	}

	/**
	 * @param patientDto
	 * @param request
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public PatientDto addPatient(@RequestBody PatientDto patientDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , patientDto.toString());
		Patient patient = PatientDto.Dto2Pojo(patientDto);
		patient = this.patientService.save(patient);
		
		if(null != patient){
			String patientPhoneNumber = patient.getMobile();
			if(patientDto.isNeedWelcomeMessage() && patientPhoneNumber !=null && patientPhoneNumber.trim().length() > 0){
				//send sms
				String msg = notifyService.getWelcomeMessage("welcome.vm",patient.getName());
				logger.debug("*********** PATIENT REG WELCOME SMS CONTENT: "+msg);		
				notifyService.sendSMS(patientPhoneNumber, msg);
			}
		}
		
		return new PatientDto(patient);
	}

	/**
	 * @param patientDto
	 * @param request
	 * @return
	 */
	@PutMapping
	@ResponseBody
	public PatientDto editPatient(@RequestBody PatientDto patientDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , patientDto.toString());
		Patient patient = PatientDto.Dto2Pojo(patientDto);
		patient = this.patientService.save(patient);
		return new PatientDto(patient);
	}
}
