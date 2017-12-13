package com.intellion.cms.web;

import java.util.ArrayList;
import java.util.Date;
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

import com.intellion.cms.domain.Address;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;
import com.intellion.cms.domain.dto.AddressDto;
import com.intellion.cms.domain.dto.PatientDto;
import com.intellion.cms.repository.AddressRepository;
import com.intellion.cms.repository.SmsDetailsRepository;
import com.intellion.cms.service.AppointmentService;
import com.intellion.cms.service.DoctorService;
import com.intellion.cms.service.NotifyService;
import com.intellion.cms.service.PatientService;
import com.intellion.cms.util.SmsContentUtil;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@Controller
@RequestMapping(value="/intelhosp/patients")
public class PatientController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
    private AddressRepository addressRepository;
	@Autowired
	private SmsDetailsRepository smsDetailsRepository;
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
	public PatientDto getPatient(@PathVariable("patientid") String patientId, HttpServletRequest request) {
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
	public void deletePatient(@PathVariable("patientid") String patientId, HttpServletRequest request) {
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
		
		AddressDto addressDto = getAddress(patientDto);
		Address address = null;
		if (addressDto != null) {
			address = this.addressRepository.save(AddressDto.Dto2Pojo(addressDto));
		}
		
		Patient patient = PatientDto.Dto2Pojo(patientDto);
		if (address != null) {patient.getAddressList().add(address);}
		patient = this.patientService.save(patient);
		
		if(null != patient){
			String patientPhoneNumber = patient.getMobileNumber1();
			if(patientDto.isNeedWelcomeMessage() && patientPhoneNumber !=null && !patientPhoneNumber.trim().isEmpty()){
				//send sms
//				String msg = notifyService.getWelcomeMessage("welcome.vm",patient.getName());
				String msg = SmsContentUtil.getInstance().getWelcomeMessage("welcome.vm", patient.getName());
				logger.debug("*********** PATIENT REG WELCOME SMS CONTENT: "+msg);
				
				SmsDetails smsDetails = new SmsDetails();
				smsDetails.setContactList(patientPhoneNumber);
				smsDetails.setDetail(msg);
				smsDetails.setDate(new Date().getTime());
				smsDetails.setName(SmsContentUtil.SMS_REG_NAME_PREFIX + patient.getId());
				smsDetails.setStatus(SmsStatus.PENDING.name());
				smsDetailsRepository.save(smsDetails);
				//notifyService.sendSMS(patientPhoneNumber, msg);
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
		AddressDto addressDto = getAddress(patientDto);
		Patient existingPatient = this.patientService.findOne(patientDto.getId());
		Patient patient = null;
		Address address = null;
		if(existingPatient.getAddressList()!=null&&existingPatient.getAddressList().size()>0){
			address = existingPatient.getAddressList().get(0);
			if (addressDto == null) {
//				this.addressRepository.delete(address);
//				patient.getAddressList().remove(0);
//				patient = this.patientService.save(patient);
				patient = PatientDto.Dto2Pojo(patientDto);
				updateModifiedParams(existingPatient, patient);
				existingPatient.getAddressList().remove(0);
//				patient.getAddressList().add(address);
				patient = this.patientService.save(existingPatient);
			} else {
				address.setApprtmentName(patientDto.getAddress1());
				address.setArea(patientDto.getAddress2());
				address.setCity(patientDto.getCity());
				address.setPincode(patientDto.getPincode());
				address.setLandLine1(patientDto.getLandline());
				address = addressRepository.save(address);
				patient = PatientDto.Dto2Pojo(patientDto);
				updateModifiedParams(existingPatient, patient);
//				patient.getAddressList().add(address);
				patient = this.patientService.save(existingPatient);
			}
		} else if (addressDto !=null){
			address = addressRepository.save(AddressDto.Dto2Pojo(addressDto));
			patient = PatientDto.Dto2Pojo(patientDto);
			updateModifiedParams(existingPatient, patient);
			existingPatient.getAddressList().add(address);
			patient = this.patientService.save(existingPatient);
		} else if (addressDto == null){
			patient = PatientDto.Dto2Pojo(patientDto);
			updateModifiedParams(existingPatient, patient);
//			patient.getAddressList().add(address);
			patient = this.patientService.save(existingPatient);
		}
		
		/*if (addressDto != null) {
			address = addressRepository.save(AddressDto.Dto2Pojo(addressDto));
		}
		
		Patient patient = PatientDto.Dto2Pojo(patientDto);

		if (address != null) {patient.getAddressList().add(address);}
		
		patient = this.patientService.save(patient);
		if ( patient.getAddressList() != null && patient.getAddressList().size() >0) {
			Address address = patient.getAddressList().get(0);
			address.setApprtmentName(patientDto.getAddress1());
			address.setArea(patientDto.getAddress2());
			address.setCity(patientDto.getCity());
			address.setPincode(patientDto.getPincode());
			address.setLandLine1(patientDto.getLandline());
			addressRepository.save(address);
		}*/
				
		return new PatientDto(patient);
	}
	private void updateModifiedParams(Patient p, Patient patient) {
		p.setId(patient.getId());
		p.setTitle(patient.getTitle());
		p.setName(patient.getName());
		p.setMobileNumber1(patient.getMobileNumber1());
		p.setProfileId(patient.getProfileId());
		p.setLabel(patient.getLabel());
		p.setBloodGroup(patient.getBloodGroup());
		p.setGender(patient.getGender());
		p.setOccupation(patient.getOccupation());
		p.setEmail(patient.getEmail());
		p.setDob(patient.getDob());
		p.setAge(patient.getAge());		
		p.setMedicalHistory(patient.getMedicalHistory());
		p.setMedicalAlert(patient.getMedicalAlert());
		p.setAllergies(patient.getAllergies());
		p.setNeedWelcomeMessage(patient.isNeedWelcomeMessage());
		p.setBirthdayWish(patient.isBirthdayWish());
		p.setRemainder(patient.getRemainder());
	}

	private AddressDto getAddress(PatientDto patientDto){
		AddressDto addressDto = null;
		if (patientDto.getAddress1() != null && !patientDto.getAddress1().trim().isEmpty()) {
			if (addressDto == null) { addressDto = new AddressDto(); }
			addressDto.setApprtmentName(patientDto.getAddress1());
		} 
		if (patientDto.getAddress2() != null&& !patientDto.getAddress2().trim().isEmpty()) {
			if (addressDto == null) { addressDto = new AddressDto(); }
			addressDto.setArea(patientDto.getAddress2());
		} 
		if (patientDto.getCity() != null&& !patientDto.getCity().trim().isEmpty()) {
			if (addressDto == null) { addressDto = new AddressDto(); }
			addressDto.setCity(patientDto.getCity());
		} 
		if (patientDto.getPincode() != null&& !patientDto.getPincode().trim().isEmpty()) {
			if (addressDto == null) { addressDto = new AddressDto(); }
			addressDto.setPincode(patientDto.getPincode());
		} 
		if (patientDto.getLandline() != null&& !patientDto.getLandline().trim().isEmpty()) {
			if (addressDto == null) { addressDto = new AddressDto(); }
			addressDto.setLandLine1(patientDto.getLandline());
		}
		return addressDto;
	}
}
