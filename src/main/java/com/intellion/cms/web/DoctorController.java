package com.intellion.cms.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intellion.cms.domain.Doctor;
import com.intellion.cms.domain.dto.DoctorDto;
import com.intellion.cms.service.AppointmentService;
import com.intellion.cms.service.DoctorService;
import com.intellion.cms.service.PatientService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping(value="/intelhosp/doctors")
public class DoctorController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * @param request
	 * @return
	 */
	@GetMapping
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
	@GetMapping(value="/{doctorid}")
	@ResponseBody
	public Doctor getDoctor(@PathVariable("doctorid") long doctorId, HttpServletRequest request) {
		return this.doctorService.findOne(doctorId);
	}
	
	@GetMapping(value="/doctorname/find")
	@ResponseBody
	public Set<DoctorDto> findByName(@RequestParam("name") String name) {
		logger.debug("Doctor Name ----> {}",name);
		Set<DoctorDto> doctorDtos = new HashSet<>();
		List<Doctor> doctors =  (List<Doctor>) this.doctorService.findByDoctorName(name);
		for (Doctor doctor:doctors) {
			doctorDtos.add(new DoctorDto(doctor));
		}
		return doctorDtos;
	}
	/**
	 * Delete Doctor
	 * @param doctorId
	 * @param request
	 */
	@DeleteMapping(value="/{doctorid}")
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
	@PutMapping
	@ResponseBody
	public DoctorDto editDoctor(@RequestBody DoctorDto doctorDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , doctorDto.toString());
		Doctor existingDoctor = this.doctorService.findOne(doctorDto.getId());
		Doctor doctor = DoctorDto.Dto2Pojo(doctorDto);
		updateModifiedParams(existingDoctor, doctor);
		doctor = this.doctorService.save(existingDoctor);
		return new DoctorDto(doctor);
	}
	private void updateModifiedParams(Doctor existingDoctor, Doctor doctor) {
		existingDoctor.setId(doctor.getId());
		existingDoctor.setTitle(doctor.getTitle());
		existingDoctor.setName(doctor.getName());
		existingDoctor.setEmail(doctor.getEmail());
		existingDoctor.setMobile1(doctor.getMobile1());
		existingDoctor.setQualification(doctor.getQualification());
		existingDoctor.setFees(doctor.getFees());
		existingDoctor.setCategoryId(doctor.getCategoryId());
	}
}
