package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.dto.DoctorDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping("category")
public class CategoryController extends DemoController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * List all doctors
	 * Using @JsonIdentityInfo
	 * In this the second time reference is replace with ObjectId
	 * Now the implementation is changed to use @JsonBackReference and @JsonBackReference
	 * Its returning only the required parameters, So chosen this approach.
	 * @param request
	 * @return
	 */
//	@GetMapping("/doctor")
//	@ResponseBody
//	public List<Doctor> getAllDoctorspojo(HttpServletRequest request) {
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
	 * @param d
	 * @param request
	 * @return
	 */
//	@PostMapping("/doctor")
//	@ResponseBody
//	public Doctor addDoctor(@RequestBody Doctor d, HttpServletRequest request) {
//		logger.debug("*********** Received the Object to ADD {}" , d.toString());
//		return this.doctorService.save(d);
//	}
	/**
	 * Edit Doctor
	 * @param d
	 * @param request
	 * @return
	 */
//	@PutMapping("/doctor")
//	@ResponseBody
//	public Doctor editDoctor(@RequestBody Doctor d, HttpServletRequest request) {
//		logger.debug("*********** Received the Object to EDIT {}" , d.toString());
//		return this.doctorService.save(d);
//	}
}
