package com.example.demo.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.domain.Doctor;
import com.example.demo.domain.Patient;
import com.example.demo.domain.Prescription;
import com.example.demo.domain.PrescriptionEntry;
import com.example.demo.domain.dto.AppointmentDto;
import com.example.demo.domain.dto.PrescriptionDto;
import com.example.demo.domain.dto.PrescriptionEntryInputDto;
import com.example.demo.domain.dto.PrescriptionInputDto;
import com.example.demo.service.DoctorService;
import com.example.demo.service.MedicationService;
import com.example.demo.service.PatientService;
import com.example.demo.service.PrescriptionEntryService;
import com.example.demo.service.PrescriptionService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@RestController
@RequestMapping(value="/intelhosp/prescription")
public class PrescriptionController {
	private static final Logger logger = LoggerFactory.getLogger(PrescriptionController.class);
	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private PrescriptionEntryService prescriptionEntryService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private MedicationService medicationService;
	/**
	 * @param request
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public List<PrescriptionDto> getAllPrescriptions(HttpServletRequest request) {
		List<Prescription> prescriptions =  (List<Prescription>) this.prescriptionService.findAll();
		List<PrescriptionDto> toReturn = new ArrayList<>();
		prescriptions.forEach(p->toReturn.add(new PrescriptionDto(p)));
		return toReturn;
	}
	
	/**
	 * Delete {@link Appointment}
	 * @param medicationId
	 * @param request
	 */
	@DeleteMapping(value="/{prescriptionid}")
	@ResponseBody
	public void deletePrescription(@PathVariable("prescriptionid") long prescriptionId, HttpServletRequest request) {
		this.prescriptionService.delete(prescriptionId);
	}

	/**
	 * Add a new {@link Appointment}
	 * @param prescriptionInputDto
	 * @param request
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public PrescriptionDto addPrescription(@RequestBody PrescriptionInputDto prescriptionInputDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , prescriptionInputDto.toString());
		Doctor doctor = doctorService.findOne(prescriptionInputDto.getDoctorId());
		Patient patient = patientService.findOne(prescriptionInputDto.getPatientId());
		
		Set<PrescriptionEntry> preEntries = new HashSet<>();
//		prescriptionInputDto.getPrescriptionEntries().forEach(x->preEntries.add(PrescriptionEntryInputDto.dto2Pojo(x)));
		for (PrescriptionEntryInputDto p : prescriptionInputDto.getPrescriptionEntries()){
			PrescriptionEntry e = PrescriptionEntryInputDto.dto2Pojo(p);
			e.setMedication(medicationService.findOne(p.getMedicationDto()));
			preEntries.add(e);
		}
		Prescription prescription = new Prescription();
		prescription.setDoctor(doctor);
		prescription.setPatient(patient);
		prescription.setDate(prescriptionInputDto.getDate());
		prescription.setPrescriptionEntries(preEntries);
		prescription = this.prescriptionService.save(prescription);
		return new PrescriptionDto(prescription);
		
	}
	/**
	 * Edit {@link Appointment}
	 * @param medicationDto
	 * @param request
	 * @return
	 */
	@PutMapping
	@ResponseBody
	public PrescriptionDto editPrescription(@RequestBody PrescriptionInputDto prescriptionInputDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , prescriptionInputDto.toString());
		Prescription prescription = prescriptionService.findOne(prescriptionInputDto.getId());
		Set<PrescriptionEntry> oldEntries = prescription.getPrescriptionEntries();
		for (PrescriptionEntry p : oldEntries){
			prescriptionEntryService.delete(p.getId());
		}
		for (PrescriptionEntryInputDto p : prescriptionInputDto.getPrescriptionEntries()){
			PrescriptionEntry e = PrescriptionEntryInputDto.dto2Pojo(p);
			e.setMedication(medicationService.findOne(p.getMedicationDto()));
			e.setPrescription(prescription);
			prescriptionEntryService.save(e);
		}
		prescription = prescriptionService.findOne(prescriptionInputDto.getId());
		return new PrescriptionDto(prescription);
	}
	
	@GetMapping(value="/presByDoc")
	public Iterable<PrescriptionDto> findByDoctorId(@RequestParam int doctorId) {
		logger.debug("Doctor ID ----> {}",doctorId);
		List<Prescription> prescriptions =  (List<Prescription>) this.prescriptionService.findByDoctor_Id(doctorId);
		List<PrescriptionDto> toReturn = new ArrayList<>();
		prescriptions.forEach(p->toReturn.add(new PrescriptionDto(p)));
		return toReturn;
	}	
	
	@GetMapping(value="/presByPat")
	public Iterable<PrescriptionDto> findByPatId(@RequestParam int patId) {
		logger.debug("Pat ID ----> {}",patId);
		List<Prescription> prescriptions =  (List<Prescription>) this.prescriptionService.findByPatient_Id(patId);
		List<PrescriptionDto> toReturn = new ArrayList<>();
		prescriptions.forEach(p->toReturn.add(new PrescriptionDto(p)));
		return toReturn;
	}
	
	@GetMapping(value="/presByDocAndPat")
	public Iterable<PrescriptionDto> findByDocAndPatId(@RequestParam int doctorId, @RequestParam int patId) {
		logger.debug("Doctor ID ----> {}",doctorId);
		logger.debug("Pat ID ----> {}",patId);
		List<Prescription> prescriptions =  (List<Prescription>) this.prescriptionService.findByDoctor_IdAndPatient_Id(doctorId, patId);
		List<PrescriptionDto> toReturn = new ArrayList<>();
		prescriptions.forEach(p->toReturn.add(new PrescriptionDto(p)));
		return toReturn;
	}
	
	
	
	
}
