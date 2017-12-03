package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.BloodGroup;
import com.example.demo.domain.Category;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.Medication;
import com.example.demo.domain.MedicationType;
import com.example.demo.domain.MedicationUnit;
import com.example.demo.domain.Patient;
import com.example.demo.domain.Prescription;
import com.example.demo.domain.PrescriptionEntry;
import com.example.demo.domain.Settings;
import com.example.demo.domain.SettingsParams;
import com.example.demo.domain.Title;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PrescriptionEntryRepository;
import com.example.demo.repository.PrescriptionRepository;
import com.example.demo.repository.SettingsParamsRepository;
import com.example.demo.repository.SettingsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {
//public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
    private PatientRepository patientRepository;
	@Autowired
    private AppointmentRepository appointmentRepository;
	@Autowired
    private DoctorRepository doctorRepository;
	@Autowired
    private CategoryRepository categoryRepository;
	@Autowired
    private MedicationRepository medicationRepository;
	@Autowired
    private PrescriptionRepository prescriptionRepository;
	@Autowired
    private PrescriptionEntryRepository prescriptionEntryRepository;
	@Autowired
    private SettingsRepository settingsRepository;
	@Autowired
    private SettingsParamsRepository settingsParamsRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... arg0) throws Exception {
		logger.info("Starting the main run method...");
		Doctor doctor = new Doctor();
		doctor.setName("Kumaraguru");
		doctor.setTitle(Title.Prof);
		doctorRepository.save(doctor);
		logger.info("Added a Doctor ...");
		
		Category dentist = new Category("dentist");
		Category root_canal_specialist = new Category("root canal specialist");
		categoryRepository.save(dentist);
		categoryRepository.save(root_canal_specialist);
		
		Set<Category> categories = new HashSet<>();
		categories.add(dentist);
//		categories.add(root_canal_specialist);
		
		Doctor d = new Doctor("Kumara");
		Doctor d1 = new Doctor("Guru");
		d.setTitle(Title.Dr);
		d.setMobile("9171415876");
		d.setCategories(categories);
		d1.setTitle(Title.Dr);
		d1.setCategories(categories);
		doctorRepository.save(d);
		doctorRepository.save(d1);
		
		Set<Doctor> doctors = new HashSet<>();
		doctors.add(d);
		doctors.add(d1);
		
		dentist.setDoctors(doctors);
//		root_canal_specialist.setDoctors(doctors);
		
		categoryRepository.save(dentist);
//		categoryRepository.save(root_canal_specialist);
		
		Patient p = new Patient("Murali Babu");
		p.setTitle(Title.Mr);
		p.setBloodGroup(BloodGroup.BPositive);
		p.setMobile("9600194696");
		p.setDob(LocalDate.of(1989, 1, 15));
		patientRepository.save(p);
		
		Appointment a = new Appointment(LocalDateTime.now(), d, p);
		appointmentRepository.save(a);
		Appointment a1 = new Appointment(LocalDateTime.now().plusDays(1), d, p);
		appointmentRepository.save(a1);
		Appointment a2 = new Appointment(LocalDateTime.now().plusDays(2), d, p);
		appointmentRepository.save(a2);
		
//		logger.debug(""+doctorRepository.findAll());
		List<Doctor> all = (List<Doctor>) doctorRepository.findAll();
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(all);
		logger.debug(""+json);

		Medication m1 = new Medication();
		m1.setName("Crosin");
		m1.setType(MedicationType.Tablet);
		Medication m2 = new Medication();
		m2.setName("benadryl");
		m2.setType(MedicationType.Syrup);
		medicationRepository.save(m1);
		medicationRepository.save(m2);
		
		Prescription prescription = new Prescription();
		prescription.setDoctor(d);
		prescription.setPatient(p);
		prescription.setDate(LocalDate.now());
		prescription = prescriptionRepository.save(prescription);
		
		PrescriptionEntry pe1 = new PrescriptionEntry();
		pe1.setBeforeFood_morning(true);
		pe1.setBeforeFood_noon(true);
		pe1.setBeforeFood_night(true);
		pe1.setMedication(m1);
		pe1.setMorning(1);
		pe1.setNoon(0);
		pe1.setNight((float)0.5);
		pe1.setNotes("Sleep well");
		pe1.setNoOfDays(3);
		pe1.setUnit_morning(MedicationUnit.Number);
		pe1.setUnit_noon(MedicationUnit.Number);
		pe1.setUnit_night(MedicationUnit.Number);
		pe1.setPrescription(prescription);
		
		PrescriptionEntry pe2 = new PrescriptionEntry();
		pe2.setBeforeFood_morning(false);
		pe1.setBeforeFood_noon(true);
		pe1.setBeforeFood_night(true);
		pe2.setMedication(m2);
		pe2.setMorning(2);
		pe2.setNoon(0);
		pe2.setNight((float)0.5);
		pe2.setNotes("Work less");
		pe2.setNoOfDays(3);
		pe2.setUnit_morning(MedicationUnit.ML);
		pe1.setUnit_noon(MedicationUnit.ML);
		pe1.setUnit_night(MedicationUnit.ML);
		pe2.setPrescription(prescription);
		
//		Set<PrescriptionEntry> prescriptionEntries = new HashSet<>();
//		prescriptionEntries.add(pe1);
//		prescriptionEntries.add(pe2);
//		prescription.setPrescriptionEntries(prescriptionEntries);
		prescriptionEntryRepository.save(pe1);
		prescriptionEntryRepository.save(pe2);
		
		Settings settings = new Settings();
		settings.setType("GLOBAL");
		settings.setCategory("SMS");
//		settings = settingsRepository.save(settings);
		
		SettingsParams settingsParams = new SettingsParams();
		settingsParams.setParamName("ENABLED");
		settingsParams.setParamValue("TRUE");
		settingsParams = settingsParamsRepository.save(settingsParams);

		SettingsParams settingsParams1 = new SettingsParams();
		settingsParams1.setParamName("REQUIRED");
		settingsParams1.setParamValue("TRUE");
		settingsParams1 = settingsParamsRepository.save(settingsParams1);
		
		settings.getSettingsParams().add(settingsParams);
		settings.getSettingsParams().add(settingsParams1);

		settings = settingsRepository.save(settings);
		logger.debug(""+settingsRepository.findAll());
	}
}
