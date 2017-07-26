package com.example.demo.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.example.demo.domain.Appointment;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.dto.AppointmentDto;
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
@RequestMapping("/intelhosp/doctors")
public class DoctorController extends DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * Using DTO copy objects Sample API
	 * @param request
	 * @return
	 */
	@GetMapping("/doctordto")
	@ResponseBody
	public List<DoctorDto> getAllDoctors(HttpServletRequest request) {
		List<DoctorDto> toReturn = new ArrayList<>();
		List<Doctor> doctors = (List<Doctor>) this.doctorService.findAll();
		toReturn = ControllerUtil.toDto(doctors);
		return toReturn;
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
	@GetMapping("/")
	@ResponseBody
	public List<DoctorDto> getAllDoctorsPojo(HttpServletRequest request) {
		List<DoctorDto> toReturn = new ArrayList<>();
		List<Doctor> doctors = (List<Doctor>) this.doctorService.findAll();
		toReturn = ControllerUtil.toDto(doctors);
		return toReturn;
	}
	
	/**
	 * Get a particular Doctor with id 
	 * @param doctorId
	 * @param request
	 * @return
	 */
	@GetMapping("/{doctorid}")
	@ResponseBody
	public Doctor getDoctor(@PathVariable("doctorid") long doctorId, HttpServletRequest request) {
		return this.doctorService.findOne(doctorId);
	}

	/**
	 * Delete Doctor
	 * @param doctorId
	 * @param request
	 */
	@DeleteMapping("/{doctorid}")
	@ResponseBody
	public void deleteDoctor(@PathVariable("doctorid") long doctorId, HttpServletRequest request) {
		this.doctorService.delete(doctorId);
	}

	/**
	 * Add a new Doctor Object
	 * @param doctor
	 * @param request
	 * @return
	 */
	/*@PostMapping(
			headers = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PostMapping
	@ResponseBody
	public DoctorDto addDoctor(@RequestBody DoctorDto doctorDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , doctorDto.toString());
		Doctor doctor = DoctorDto.Dto2Pojo(doctorDto);
		doctor = this.doctorService.save(doctor);
		return new DoctorDto(doctor);
	}
	/**
	 * Edit Doctor
	 * @param doctor
	 * @param request
	 * @return
	 */
	/*@PutMapping(
			headers = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping
	@ResponseBody
	public DoctorDto editDoctor(@RequestBody DoctorDto doctorDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , doctorDto.toString());
		Doctor doctor = DoctorDto.Dto2Pojo(doctorDto);
		doctor = this.doctorService.save(doctor);
		return new DoctorDto(doctor);
	}
}
