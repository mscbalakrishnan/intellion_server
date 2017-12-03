package com.example.demo.web;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.example.demo.domain.Appointment;
import com.example.demo.domain.dto.AppointmentDto;
import com.example.demo.domain.dto.AppointmentInputDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.NotifyService;
import com.example.demo.service.PatientService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@RestController
@RequestMapping(value="/intelhosp/appointments")
public class AppointmentController {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private NotifyService notifyService;

	/**
	 * @param request
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public Set<AppointmentDto> getAllAppointmentPojo(HttpServletRequest request) {
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		List<Appointment> appointments =  (List<Appointment>) this.appointmentService.findAll();
		for (Appointment appointment:appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}
	
	@GetMapping(value="/doctor")
	public Iterable<AppointmentDto> findByDoctorId(@RequestParam int doctorId) {
		logger.debug("Doctor ID ----> {}",doctorId);
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		List<Appointment> appointments =  (List<Appointment>) this.appointmentService.findByDoctorId(doctorId);
		for (Appointment appointment:appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}
	@GetMapping(value="/doctorname")
	public Iterable<AppointmentDto> findByDoctorName(@RequestParam String name) {
		logger.debug("Doctor Name ----> {}",name);
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		List<Appointment> appointments =  (List<Appointment>) this.appointmentService.findByDoctorName(name);
		for (Appointment appointment:appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}
	@GetMapping(value="/patient")
	public Iterable<AppointmentDto> findByPatientId(@RequestParam int patientId) {
		logger.debug("Patient ID ----> {}",patientId);
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		List<Appointment> appointments =  (List<Appointment>) this.appointmentService.findByPatientId(patientId);
		for (Appointment appointment:appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}
	
	@GetMapping(value="/upto")
	public Iterable<AppointmentDto> findByDateBefore(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		logger.debug("Upto Date ----> {}",date);
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		List<Appointment> appointments =  (List<Appointment>) this.appointmentService.findByTimeBefore(date);
		for (Appointment appointment:appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}
	@GetMapping(value="/from")
	public Iterable<AppointmentDto> findByDateAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		logger.debug("From Date ----> {}",date);
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		List<Appointment> appointments =  (List<Appointment>) this.appointmentService.findByTimeAfter(date);
		for (Appointment appointment:appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}
	@GetMapping(value="/betweendates")
	public Iterable<AppointmentDto> findByDateBetween(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
		logger.debug("FROM date ----> {}",from);
		logger.debug("TO date ----> {}",to);
		Set<AppointmentDto> appointmentDtos = new HashSet<>();
		List<Appointment> appointments =  (List<Appointment>) this.appointmentService.findByTimeBetween(from, to);
		for (Appointment appointment:appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}
	/**
	 * Get a particular {@link Appointment} with id 
	 * @param appointmentId
	 * @param request
	 * @return
	 */
	@GetMapping(value="/{appointmentid}")
	@ResponseBody
	public AppointmentDto getAppointment(@PathVariable("appointmentid") long appointmentId, HttpServletRequest request) {
		return new AppointmentDto(this.appointmentService.findOne(appointmentId).get());
	}

	/**
	 * Delete {@link Appointment}
	 * @param appointmentId
	 * @param request
	 */
	@DeleteMapping(value="/{appointmentid}")
	@ResponseBody
	public void deleteAppointment(@PathVariable("appointmentid") long appointmentId, HttpServletRequest request) {
				
		logger.debug("*********** deleteAppointment Inside");
		AppointmentDto appointmentDto =  new AppointmentDto(this.appointmentService.findOne(appointmentId).get());
		
		String doctorPhoneNumber = appointmentDto.getDoctor().getMobile();
		String patientPhoneNumber = appointmentDto.getPatient().getMobile();
		
		this.appointmentService.delete(appointmentId);
		
		if (patientPhoneNumber != null && patientPhoneNumber.trim().length() != 0) {
			String msg = notifyService.getMsgForDelApp("app_cancel.vm",appointmentDto,appointmentDto.getPatient().getName());
			logger.debug("*********** CANCEL Appointment SMS MESSAGE TO PATIENT: "+msg);
			notifyService.sendSMS(patientPhoneNumber, msg);
		}
		
		if (doctorPhoneNumber != null && doctorPhoneNumber.trim().length() != 0) {
			String msg = notifyService.getMsgForDelApp("app_cancel.vm",appointmentDto,"Dr "+appointmentDto.getDoctor().getName());
			logger.debug("*********** CANCEL Appointment SMS MESSAGE TO DOCTOR: "+msg);
			notifyService.sendSMS(doctorPhoneNumber, msg);
		}
	}

	/**
	 * Add a new {@link Appointment}
	 * @param appointmentInputDto
	 * @param request
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public AppointmentDto addAppointment(@RequestBody AppointmentInputDto appointmentInputDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , appointmentInputDto.toString());
		Appointment appointment = new Appointment(appointmentInputDto.getTime(),doctorService.findOne(appointmentInputDto.getDoctorId()).get(),patientService.findOne(appointmentInputDto.getPatientId()));
		appointment = this.appointmentService.save(appointment);
		if (appointment != null) {
			// Success process sms
			
			if (appointmentInputDto.isSmsToDoctor()) {
				String doctorPhoneNumber = appointment.getDoctor().getMobile();
				String msg = notifyService.getMsgForApp("app_confirm.vm",appointment,"Dr "+appointment.getDoctor().getName());
				logger.debug("*********** APPOINTMENT BOOKING SMS CONTENT FOR DOCTOR: "+msg);
				if (doctorPhoneNumber != null && doctorPhoneNumber.trim().length() !=0) {
					notifyService.sendSMS(doctorPhoneNumber, msg);
				}
			}
			if (appointmentInputDto.isSmsToPatient()) {
				String patientPhoneNumber = appointment.getPatient().getMobile();
				String msg = notifyService.getMsgForApp("app_confirm.vm",appointment,appointment.getPatient().getName());
				logger.debug("*********** APPOINTMENT BOOKING SMS CONTENT FOR PAT: "+msg);
				if (patientPhoneNumber != null && patientPhoneNumber.trim().length() != 0) {
					notifyService.sendSMS(patientPhoneNumber, msg);
				}
			}
		}
		return new AppointmentDto(appointment);
		
	}
	/**
	 * Edit {@link Appointment}
	 * @param appointmentInputDto
	 * @param request
	 * @return
	 */
	@PutMapping
	@ResponseBody
	public AppointmentDto editAppointment(@RequestBody AppointmentInputDto appointmentInputDto, HttpServletRequest request) {
		Appointment appointment = null;
		logger.debug("*********** Received the Object to EDIT {}" , appointmentInputDto.toString());
		Optional<Appointment> optional = appointmentService.findOne(appointmentInputDto.getId());
		if(optional.isPresent()) {
			appointment = optional.get();
			appointment.setTime(appointmentInputDto.getTime());
			appointment.setDoctor(doctorService.findOne(appointmentInputDto.getDoctorId()).get());
			appointment.setPatient(patientService.findOne(appointmentInputDto.getPatientId()));
			appointment = this.appointmentService.save(appointment);
		} else {
//			return null;
		}
		
		if (appointment != null) {
			// Success process sms
			
			if (appointmentInputDto.isSmsToDoctor()) {
				String doctorPhoneNumber = appointment.getDoctor().getMobile();
				String msg = notifyService.getMsgForApp("app_reschedule.vm",appointment,"Dr "+appointment.getDoctor().getName());
				logger.debug("*********** RESCHEDULE SMS CONTENT FOR DOCTOR:"+msg);
				if (doctorPhoneNumber != null && doctorPhoneNumber.trim().length() !=0) {
					notifyService.sendSMS(doctorPhoneNumber, msg);
				}
			}
			if (appointmentInputDto.isSmsToPatient()) {
				String patientPhoneNumber = appointment.getPatient().getMobile();

				String msg = notifyService.getMsgForApp("app_reschedule.vm",appointment,appointment.getPatient().getName());
				logger.debug("*********** RESCHEDULE SMS CONTENT FOR PAT:"+msg);
				if (patientPhoneNumber != null && patientPhoneNumber.trim().length() != 0) {
					notifyService.sendSMS(patientPhoneNumber, msg);
				}
			}
		}
		
		
		
		return new AppointmentDto(appointment);
	}
}
