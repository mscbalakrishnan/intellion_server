package com.intellion.cms.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.Doctor;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.Prescription;
import com.intellion.cms.domain.PrescriptionEntry;
import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.dto.ClinicDto;
import com.intellion.cms.domain.dto.PatientDto;
import com.intellion.cms.domain.dto.PrescriptionDto;
import com.intellion.cms.domain.dto.PrescriptionEntryDto;
import com.intellion.cms.domain.dto.PrescriptionEntryInputDto;
import com.intellion.cms.domain.dto.PrescriptionInputDto;
import com.intellion.cms.domain.dto.ReportDto;
import com.intellion.cms.domain.dto.SettingsDto;
import com.intellion.cms.pdf.PDFGenerator;
import com.intellion.cms.pdf.PropertyInfo;
import com.intellion.cms.pdf.SummaryRequest;
import com.intellion.cms.pdf.SummarySection;
import com.intellion.cms.pdf.TableData;
import com.intellion.cms.service.DoctorService;
import com.intellion.cms.service.MedicationService;
import com.intellion.cms.service.PatientService;
import com.intellion.cms.service.PrescriptionEntryService;
import com.intellion.cms.service.PrescriptionService;
import com.intellion.cms.service.SettingsService;
import com.intellion.cms.util.CommonUtil;
import com.intellion.cms.vm.VmConfigGenerator;

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
	@Autowired
	private SettingsService settingsService;
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
		Doctor doctor =  doctorService.findOne(prescriptionInputDto.getDoctorId());
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
	
	@GetMapping(value="/doctor/{doctorid}")
	public Iterable<PrescriptionDto> findByDoctorId(@PathVariable("doctorid") long doctorId, HttpServletRequest request) {
		logger.debug("Doctor ID ----> {}",doctorId);
		List<Prescription> prescriptions =  (List<Prescription>) this.prescriptionService.findByDoctor_Id(doctorId);
		List<PrescriptionDto> toReturn = new ArrayList<>();
		prescriptions.forEach(p->toReturn.add(new PrescriptionDto(p)));
		return toReturn;
	}	
	
	@GetMapping(value="/patient/{patientid}")
	public Iterable<PrescriptionDto> findByPatId(@PathVariable("patientid") String patientId, HttpServletRequest request) {
		logger.debug("Patient Id ----> {}",patientId);
		List<Prescription> prescriptions =  (List<Prescription>) this.prescriptionService.findByPatient_Id(patientId);
		List<PrescriptionDto> toReturn = new ArrayList<>();
		prescriptions.forEach(p->toReturn.add(new PrescriptionDto(p)));
		return toReturn;
	}
	
	@GetMapping(value="/doctorandpatient/{doctorid}/{patientid}")
	public Iterable<PrescriptionDto> findByDocAndPatId(@PathVariable("doctorid") long doctorId, @PathVariable("patientid") String patientId, HttpServletRequest request) {
		logger.debug("Doctor ID ----> {}, Patient ID ----> {}",doctorId, patientId);
		List<Prescription> prescriptions =  (List<Prescription>) this.prescriptionService.findByDoctor_IdAndPatient_Id(doctorId, patientId);
		List<PrescriptionDto> toReturn = new ArrayList<>();
		prescriptions.forEach(p->toReturn.add(new PrescriptionDto(p)));
		return toReturn;
	}
	
	@GetMapping(value="/{prescriptionid}")
	@ResponseBody
	public PrescriptionDto getPrescription(@PathVariable("prescriptionid") long prescriptionId, HttpServletRequest request) {
		logger.debug("PresID ID ---->",prescriptionId);
		Prescription prescription = this.prescriptionService.findOne(prescriptionId);
		return new PrescriptionDto(prescription);
	}	
	
	@GetMapping(value="/print/{prescriptionid}")
	@ResponseBody
	public ResponseEntity<byte[]> printPrescription(@PathVariable("prescriptionid") long prescriptionId, HttpServletRequest request) {
//	public ReportDto printPrescription(@PathVariable("prescriptionid") long prescriptionId, HttpServletRequest request) {
		logger.debug("*********** Received the Object to print {}" , prescriptionId);
		List<Settings> settingsListByCat =  (List<Settings>)this.settingsService.findByCategory("clinic");
		List<SettingsDto> toReturn = new ArrayList<>();
		settingsListByCat.forEach(s->toReturn.add(new SettingsDto(s)));
		SettingsDto settingsDto = toReturn.get(0);
		logger.debug("settingsDto --> {}", settingsDto);
		ClinicDto clinicDto = settingsDto.getClinicObject();
		logger.debug("clinicDto --> {}", clinicDto);
		Prescription prescription = prescriptionService.findOne(prescriptionId);
		PrescriptionDto pDto = new PrescriptionDto(prescription);
		Patient patient = patientService.findOne(pDto.getPatientDto().getId());
		PatientDto patientDto = new PatientDto(patient);
		VmConfigGenerator generator = new VmConfigGenerator();
		String prescription_html_str = generator.generatePrescriptionConfiguration(pDto, clinicDto, patientDto);
		String contents = prescription_html_str.replace("\r\n", "");
		logger.debug("report contents --> {}", contents);
//		return new ReportDto(contents);
		
		
		///////////Start
		SummaryRequest summary = new SummaryRequest();
		summary.setTitle(clinicDto.getClinicName());
		summary.setSubTitle(clinicDto.getAddressLine1() + " " + clinicDto.getAddressLine2() + " " + clinicDto.getArea()
				+ " " + clinicDto.getCity() + "-" + clinicDto.getPincode() + " Email:" + clinicDto.getEmail() + " Web:"
				+ clinicDto.getWebsite() + " Mobile:" + clinicDto.getMobile() + " Landline:" + clinicDto.getLandline());
		List<SummarySection> sectionList = new ArrayList<SummarySection>();
		
		SummarySection labelSection = new SummarySection();
		List<PropertyInfo> propertyList = new ArrayList<PropertyInfo>();
		propertyList.add(new PropertyInfo("Date:", pDto.getDate().toString()));
		propertyList.add(new PropertyInfo("Doctor:", pDto.getDoctorDto().getName()));
		propertyList.add(new PropertyInfo("Name:", pDto.getPatientDto().getName()));
		propertyList.add(new PropertyInfo("Age:", String.valueOf(pDto.getPatientDto().getAge())));
		propertyList.add(new PropertyInfo("Gender:", pDto.getPatientDto().getGender().name()));
		labelSection.setValues(propertyList);
		sectionList.add(labelSection);
		
		
		SummarySection tableSection = new SummarySection();
		tableSection.setTitle("Medications");
		List<TableData> tableDataList = new ArrayList<TableData>();
		TableData tableData = new TableData();
		
		ArrayList<String> headings = new ArrayList<String>(Arrays.asList("Medicine", "Days", "Morning", "Noon", "Night", "Notes"));
		tableData.setTableHeading(headings);
		List<List<String>> rowData = new ArrayList<List<String>>();
		
		for (PrescriptionEntryDto pe: pDto.getPrescriptionEntries()){
			String morning_str = String.valueOf(pe.getMorning());
			if (pe.isBeforeFood_morning()) {
				morning_str += "-BF";
			} else {
				morning_str += "-AF";
			}
			String noon_str = String.valueOf(pe.getMorning());
			if (pe.isBeforeFood_morning()) {
				noon_str += "-BF";
			} else {
				noon_str += "-AF";
			}
			String night_str = String.valueOf(pe.getMorning());
			if (pe.isBeforeFood_morning()) {
				night_str += "-BF";
			} else {
				night_str += "-AF";
			}
			ArrayList<String> row1 = new ArrayList<String>(Arrays.asList(pe.getMedicationDto().getName(),
					String.valueOf(pe.getNoOfDays()), morning_str, noon_str, night_str, pe.getNotes()));
			rowData.add(row1);
		}
		
		tableData.setTableData(rowData);
		tableDataList.add(tableData);
		tableSection.setTableDataList(tableDataList);
		sectionList.add(tableSection);
		
		summary.setSectionList(sectionList);
		
		PDFGenerator.generatePdfSummaryReport(summary, "SummaryReport.pdf");
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		String path = classloader.getResource("").getPath();
		String osAppropriatePath = System.getProperty( "os.name" ).contains( "indow" ) ? path.substring(1) : path;
		osAppropriatePath = CommonUtil.getFilePath(osAppropriatePath);
		String FILE_SEPERATOR = File.separator;
		String imageLocation = path + ".." + FILE_SEPERATOR + ".." + FILE_SEPERATOR + "export" + FILE_SEPERATOR
				+ "jrxml" + FILE_SEPERATOR;
		String pdfLocation = osAppropriatePath + "../../Reports/";
		
		
		Path pdfPath = Paths.get(pdfLocation+"SummaryReport.pdf");
		byte[] pdf_bytes = null;
		try {
			pdf_bytes = Files.readAllBytes(pdfPath);
			logger.debug("pdf_bytes size ::: {}", pdf_bytes.length);
		} catch (IOException e) {
			logger.error("Exception:::", e);
		}
		///////////End
		
		
		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    String filename = "output.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdf_bytes, headers, HttpStatus.OK);
	    return response;
	}
	
}
