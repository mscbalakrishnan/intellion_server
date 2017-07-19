package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping("/intelhosp")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/settings")
	@ResponseBody
	public String settings(HttpServletRequest request) {
		logger.debug("*********** Received the Object to Set ");
		return "Hello World";
	}
	/**
	 * List all doctors
	 * Using @JsonIdentityInfo
	 * In this the second time reference is replace with ObjectId
	 * Now the implementation is changed to use @JsonBackReference and @JsonBackReference
	 * Its returning only the required parameters, So chosen this approach.
	 * @param request
	 * @return
	 */
//	@GetMapping("/doctor/")
//	@ResponseBody
//	public List<Doctor> getAllDoctorsPojo(HttpServletRequest request) {
//		return (List<Doctor>) this.doctorService.findAll();
//	}
	
	/**
	 * Get a particular Doctor with id 
	 * @param doctorId
	 * @param request
	 * @return
	 */
//	@GetMapping("/doctor/{doctorid}")
//	@ResponseBody
//	public Doctor getDoctor(@PathVariable("doctorid") long doctorId, HttpServletRequest request) {
//		return this.doctorService.findOne(doctorId);
//	}

	/**
	 * Delete Doctor
	 * @param doctorId
	 * @param request
	 */
//	@DeleteMapping("/doctor/{doctorid}")
//	@ResponseBody
//	public void deleteDoctor(@PathVariable("doctorid") long doctorId, HttpServletRequest request) {
//		this.doctorService.delete(doctorId);
//	}

	/**
	 * Add a new Doctor Object
	 * @param doctor
	 * @param request
	 * @return
	 */
//	@PostMapping(
//			value="/doctor/",
//			headers = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE }, 
//			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@ResponseBody
//	public Doctor addDoctor(@RequestBody Doctor doctor, HttpServletRequest request) {
//		logger.debug("*********** Received the Object to ADD {}" , doctor.toString());
//		return this.doctorService.save(doctor);
//	}
	/**
	 * Edit Doctor
	 * @param doctor
	 * @param request
	 * @return
	 */
//	@PutMapping(
//			value="/doctor/",
//			headers = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE }, 
//			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@ResponseBody
//	public Doctor editDoctor(@RequestBody Doctor doctor, HttpServletRequest request) {
//		logger.debug("*********** Received the Object to EDIT {}" , doctor.toString());
//		return this.doctorService.save(doctor);
//	}
}
