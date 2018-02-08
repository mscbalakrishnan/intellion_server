package com.intellion.cms.web;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;
import com.intellion.cms.domain.dto.AppointmentDto;
import com.intellion.cms.domain.dto.AppointmentInputDto;
import com.intellion.cms.repository.SmsDetailsRepository;
import com.intellion.cms.service.AppointmentService;
import com.intellion.cms.service.DoctorService;
import com.intellion.cms.service.PatientService;
import com.intellion.cms.util.DateUtil;
import com.intellion.cms.util.SmsContentUtil;

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
	private SmsDetailsRepository smsDetailsRepository;
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
	public Iterable<AppointmentDto> findByPatientId(@RequestParam String patientId) {
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
		return new AppointmentDto(this.appointmentService.findOne(appointmentId));
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
		Appointment appointment = this.appointmentService.findOne(appointmentId);
		AppointmentDto appointmentDto =  new AppointmentDto(appointment);
		String doctorPhoneNumber = appointmentDto.getDoctor().getMobile();
		String patientPhoneNumber = appointmentDto.getPatient().getMobile();
		
		this.appointmentService.delete(appointmentId);
		
		if (doctorPhoneNumber != null && doctorPhoneNumber.trim().length() != 0) {
			sendSmsToDoctor(appointment, "appointment_cancel.vm",SmsContentUtil.SMS_APPOINTMENT_CANCEL_NAME_PREFIX);
		}
		if (patientPhoneNumber != null && patientPhoneNumber.trim().length() != 0) {
			sendSmsToPatient(appointment, "appointment_cancel.vm",SmsContentUtil.SMS_APPOINTMENT_CANCEL_NAME_PREFIX);
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
		Appointment appointment = new Appointment(appointmentInputDto.getTime(),doctorService.findOne(appointmentInputDto.getDoctorId()),patientService.findOne(appointmentInputDto.getPatientId()));
		appointment = this.appointmentService.save(appointment);
		if (appointment != null) {
			// Success process sms
			if (appointmentInputDto.isSmsToDoctor()) {
				sendSmsToDoctor(appointment, "appointment_confirm.vm",SmsContentUtil.SMS_APPOINTMENT_NAME_PREFIX);
			}
			if (appointmentInputDto.isSmsToPatient()) {
				sendSmsToPatient(appointment, "appointment_confirm.vm",SmsContentUtil.SMS_APPOINTMENT_NAME_PREFIX);
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
		logger.debug("*********** Received the Object to EDIT {}" , appointmentInputDto.toString());
		Appointment appointment = appointmentService.findOne(appointmentInputDto.getId());
		appointment.setTime(appointmentInputDto.getTime());
		appointment.setDoctor(doctorService.findOne(appointmentInputDto.getDoctorId()));
		appointment.setPatient(patientService.findOne(appointmentInputDto.getPatientId()));
		appointment = this.appointmentService.save(appointment);
		
		if (appointment != null) {
			// Success process sms
			if (appointmentInputDto.isSmsToDoctor()) {
				sendSmsToDoctor(appointment, "appointment_reschedule.vm",SmsContentUtil.SMS_APPOINTMENT_RESCHEDULE_NAME_PREFIX);
			}
			if (appointmentInputDto.isSmsToPatient()) {
				sendSmsToPatient(appointment, "appointment_reschedule.vm",SmsContentUtil.SMS_APPOINTMENT_RESCHEDULE_NAME_PREFIX);
			}
		}
		logger.debug("edited successfully...");
		return new AppointmentDto(appointment);
	}

	private void sendSmsToDoctor(Appointment appointment, String templateName, String smsIdentifier) {
		String doctorPhoneNumber = appointment.getDoctor().getMobile1();
		String doctorName = appointment.getDoctor().getName();
		Long doctorId = appointment.getDoctor().getId();
		String patientName = appointment.getPatient().getName();
		String time = DateUtil.getShortDateTime(appointment.getTime());
		String msg = SmsContentUtil.getInstance().getMsgForSms(templateName, patientName, doctorName, time);
		logger.debug("*********** SMS CONTENT FOR DOCTOR:"+msg);
		if (doctorPhoneNumber != null && !doctorPhoneNumber.trim().isEmpty()) {
			SmsDetails smsDetails = new SmsDetails();
			smsDetails.setContactList(doctorPhoneNumber);
			smsDetails.setRetryCount(5);
			smsDetails.setDetail(msg);
			smsDetails.setDate(new Date().getTime());
			smsDetails.setName(smsIdentifier + doctorId.toString());
			smsDetails.setStatus(SmsStatus.PENDING.name());
			smsDetailsRepository.save(smsDetails);
		}
	}

	private void sendSmsToPatient(Appointment appointment, String templateName, String smsIdentifier) {
		String patientId = appointment.getPatient().getId();
		String patientName = appointment.getPatient().getName();
		String patientPhoneNumber = appointment.getPatient().getMobileNumber1();
		String time = DateUtil.getShortDateTime(appointment.getTime());
		String msg = SmsContentUtil.getInstance().getMsgForSms(templateName, patientName, null, time);
		logger.debug("*********** SMS CONTENT FOR PATIENT:"+msg);
		if (patientPhoneNumber != null && patientPhoneNumber.trim().length() != 0) {
			SmsDetails smsDetails = new SmsDetails();
			smsDetails.setContactList(patientPhoneNumber);
			smsDetails.setDetail(msg);
			smsDetails.setRetryCount(5);
			smsDetails.setDate(new Date().getTime());
			smsDetails.setName(smsIdentifier + patientId);
			smsDetails.setStatus(SmsStatus.PENDING.name());
			smsDetailsRepository.save(smsDetails);
		}
	}
}
