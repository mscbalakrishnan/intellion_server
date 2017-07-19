package com.example.demo.web;

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

import com.example.demo.domain.Appointment;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.dto.AppointmentDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping("/intelhosp/appointments")
public class AppointmentController extends DemoController {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
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
	@GetMapping
	@ResponseBody
	public List<Appointment> getAllAppointmentPojo(HttpServletRequest request) {
		return (List<Appointment>) this.appointmentService.findAll();
	}
	
	/**
	 * Get a particular {@link Appointment} with id 
	 * @param appointmentId
	 * @param request
	 * @return
	 */
	@GetMapping("/{appointmentid}")
	@ResponseBody
	public Appointment getAppointment(@PathVariable("appointmentid") long appointmentId, HttpServletRequest request) {
		return this.appointmentService.findOne(appointmentId);
	}

	/**
	 * Delete {@link Appointment}
	 * @param appointmentId
	 * @param request
	 */
	@DeleteMapping("/{appointmentid}")
	@ResponseBody
	public void deleteAppointment(@PathVariable("appointmentid") long appointmentId, HttpServletRequest request) {
		this.appointmentService.delete(appointmentId);
	}

	/**
	 * Add a new {@link Appointment}
	 * @param appointmentDto
	 * @param request
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public AppointmentDto addAppointment(@RequestBody AppointmentDto appointmentDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , appointmentDto.toString());
		Appointment appointment = this.appointmentService.save(appointmentDto.getId(),appointmentDto.getTime(),appointmentDto.getDoctor(),appointmentDto.getPatient());
		return new AppointmentDto(appointment);
		
	}
	/**
	 * Edit {@link Appointment}
	 * @param appointmentDto
	 * @param request
	 * @return
	 */
	@PutMapping
	@ResponseBody
	public AppointmentDto editAppointment(@RequestBody AppointmentDto appointmentDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , appointmentDto.toString());
		Appointment appointment = this.appointmentService.save(appointmentDto.getId(),appointmentDto.getTime(),appointmentDto.getDoctor(),appointmentDto.getPatient());
		return new AppointmentDto(appointment);
	}
}
