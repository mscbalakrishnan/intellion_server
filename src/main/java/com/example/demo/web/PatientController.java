package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.Patient;
import com.example.demo.domain.dto.DoctorDto;
import com.example.demo.domain.dto.PatientDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping("/intelhosp/patients")
public class PatientController extends DemoController {
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * List all patients
	 * Using @JsonIdentityInfo
	 * In this the second time reference is replace with ObjectId
	 * Now the implementation is changed to use @JsonBackReference and @JsonBackReference
	 * Its returning only the required parameters, So chosen this approach.
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
	 * Get a particular Doctor with id 
	 * @param patientId
	 * @param request
	 * @return
	 */
	@GetMapping("/{patientid}")
	@ResponseBody
	public PatientDto getPatient(@PathVariable("patientid") long patientId, HttpServletRequest request) {
		return new PatientDto(this.patientService.findOne(patientId));
	}

	/**
	 * Delete Doctor
	 * @param patientId
	 * @param request
	 */
	@DeleteMapping("/{patientid}")
	@ResponseBody
	public void deletePatient(@PathVariable("patientid") long patientId, HttpServletRequest request) {
		this.patientService.delete(patientId);
	}

	/**
	 * Add a new Doctor Object
	 * @param d
	 * @param request
	 * @return
	 */
	/*@PostMapping(
			value="/",
			headers={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})*/
	@PostMapping
	@ResponseBody
	public PatientDto addPatient(@RequestBody PatientDto patientDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , patientDto.toString());
		Patient patient = PatientDto.Dto2Pojo(patientDto);
		patient = this.patientService.save(patient);
		return new PatientDto(patient);
	}
	/**
	 * Edit Doctor
	 * @param patient
	 * @param request
	 * @return
	 */
	/*@PutMapping(value = "/{patientid}", 
			headers = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping
	@ResponseBody
	public PatientDto editPatient(@RequestBody PatientDto patientDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , patientDto.toString());
		Patient patient = PatientDto.Dto2Pojo(patientDto);
		patient = this.patientService.save(patient);
		return new PatientDto(patient);
	}
}
