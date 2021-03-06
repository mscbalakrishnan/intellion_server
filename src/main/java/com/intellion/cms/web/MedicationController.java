package com.intellion.cms.web;

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

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.Medication;
import com.intellion.cms.domain.dto.MedicationDto;
import com.intellion.cms.service.MedicationService;

/**
 * Main Controller class
 * @author Kumaraguru_Narayanan
 *
 */
@RestController
@RequestMapping(value="/intelhosp/medications")
public class MedicationController {
	private static final Logger logger = LoggerFactory.getLogger(MedicationController.class);
	@Autowired
	private MedicationService medicationService;

	/**
	 * @param request
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public List<MedicationDto> getAllMedications(HttpServletRequest request) {
		List<Medication> patients =  (List<Medication>) this.medicationService.findAll();
		List<MedicationDto> toReturn = new ArrayList<>();
		patients.forEach(p->toReturn.add(new MedicationDto(p)));
		return toReturn;
	}
	
	/**
	 * Delete {@link Appointment}
	 * @param medicationId
	 * @param request
	 */
	@DeleteMapping(value="/{medicationid}")
	@ResponseBody
	public void deleteMedication(@PathVariable("medicationid") long medicationId, HttpServletRequest request) {
		this.medicationService.delete(medicationId);
	}

	/**
	 * Add a new {@link Appointment}
	 * @param medicationDto
	 * @param request
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public MedicationDto addMedication(@RequestBody MedicationDto medicationDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to ADD {}" , medicationDto.toString());
		Medication medication = new Medication();
		medication.setName(medicationDto.getName());
		medication.setType(medicationDto.getType());
		medication = this.medicationService.save(medication);
		return new MedicationDto(medication);
		
	}
	/**
	 * Edit {@link Appointment}
	 * @param medicationDto
	 * @param request
	 * @return
	 */
	@PutMapping
	@ResponseBody
	public MedicationDto editMedication(@RequestBody MedicationDto medicationDto, HttpServletRequest request) {
		logger.debug("*********** Received the Object to EDIT {}" , medicationDto.toString());
		Medication medication = medicationService.findOne(medicationDto.getId());
		medication.setName(medicationDto.getName());
		medication.setType(medicationDto.getType());
		medication = this.medicationService.save(medication);
		return new MedicationDto(medication);
	}
	
	@GetMapping(value="/find")
	@ResponseBody
	public Set<MedicationDto> findByName(@RequestParam("name") String name) {
		logger.debug("Medication Name ----> {}",name);
		Set<MedicationDto> medicationDtos = new HashSet<>();
		List<Medication> medications =  (List<Medication>) this.medicationService.findByMedicationName(name);
		for (Medication medication:medications) {
			medicationDtos.add(new MedicationDto(medication));
		}
		return medicationDtos;
	}	
	
	
	
}
