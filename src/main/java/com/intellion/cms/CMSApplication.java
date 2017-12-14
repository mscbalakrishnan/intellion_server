package com.intellion.cms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.intellion.cms.domain.Address;
import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.BloodGroup;
import com.intellion.cms.domain.Category;
import com.intellion.cms.domain.Doctor;
import com.intellion.cms.domain.Medication;
import com.intellion.cms.domain.MedicationType;
import com.intellion.cms.domain.MedicationUnit;
import com.intellion.cms.domain.Patient;
import com.intellion.cms.domain.Prescription;
import com.intellion.cms.domain.PrescriptionEntry;
import com.intellion.cms.domain.Settings;
import com.intellion.cms.domain.SettingsParams;
import com.intellion.cms.domain.Title;
import com.intellion.cms.repository.AddressRepository;
import com.intellion.cms.repository.AppointmentRepository;
import com.intellion.cms.repository.CategoryRepository;
import com.intellion.cms.repository.DoctorRepository;
import com.intellion.cms.repository.MedicationRepository;
import com.intellion.cms.repository.PatientRepository;
import com.intellion.cms.repository.PrescriptionEntryRepository;
import com.intellion.cms.repository.PrescriptionRepository;
import com.intellion.cms.repository.SettingsParamsRepository;
import com.intellion.cms.repository.SettingsRepository;


@SpringBootApplication
@EnableScheduling
public class CMSApplication implements CommandLineRunner {
//public class CMSApplication {
	private final Logger logger = LoggerFactory.getLogger(getClass());
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
	@Autowired
    private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CMSApplication.class, args);
	}

	/*@Override
	@PostConstruct
	public void run(String... arg0) throws Exception {
		Thread.sleep(5000);
		Role _adminRole = new Role();
		_adminRole.setRole("ADMIN");
		roleRepository.save(_adminRole);
		Role _userRole = new Role();
		_userRole.setRole("USER");
		roleRepository.save(_userRole);
		User user = new User();
		user.setName("admine");
		Role userRole = roleRepository.findByRole("ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		user.setRoles(roles);
		user.setActive(true);
		user.setPassword(bCryptPasswordEncoder.encode("admin"));
		userRepository.save(user);
	}*/
	@Override
	public void run(String... arg0) throws Exception {
		logger.info("Starting the main run method...");
		Doctor doctor = new Doctor();
		doctor.setName("Kumaraguru");
		doctor.setMobile1("9876543321");
		doctor.setTitle(Title.Prof);
		doctor.setEmail("kumara@guru.com");
		doctor = doctorRepository.save(doctor);
		logger.info("Added a Doctor ...");
		
		Category dentist = new Category("dentist");
		Category root_canal_specialist = new Category("root canal specialist");
		dentist = categoryRepository.save(dentist);
		root_canal_specialist = categoryRepository.save(root_canal_specialist);
		
		Set<Category> categories = new HashSet<>();
		categories.add(dentist);
//		categories.add(root_canal_specialist);
		
		Doctor d = new Doctor();
		d.setName("Kumara");
		Doctor d1 = new Doctor();
		d.setTitle(Title.Dr);
		d.setEmail("abc@xyz.com");
		d.setMobile1("9171415876");
		d.setCategories(categories);
		d = doctorRepository.save(d);
		d1.setName("Guru");
		d1.setTitle(Title.Dr);
		d1.setEmail("xyz@abc.com");
		d1.setCategories(categories);
		d1.setMobile1("9971415876");
		d1 = doctorRepository.save(d1);
		
		Set<Doctor> doctors = new HashSet<>();
		doctors.add(d);
		doctors.add(d1);
		
		dentist.setDoctors(doctors);
//		root_canal_specialist.setDoctors(doctors);
		
		categoryRepository.save(dentist);
//		categoryRepository.save(root_canal_specialist);
		
		Address address1 = new Address();
		address1.setApprtmentName("33, Abc Apts");
		address1.setArea("Velachery");
		address1.setCity("Chennai");
		address1.setCountry("India");
		address1 = addressRepository.save(address1);
		Patient p = new Patient();
		p.setName("Murali Babu");
		p.setMobileNumber1("9600194696");
		p.setTitle(Title.Mr);
		p.setBloodGroup(BloodGroup.BPositive);
		p.setDob(LocalDate.of(1989, 1, 15));
		p.getAddressList().add(address1);
		p = patientRepository.save(p);
		
		Appointment a = new Appointment(LocalDateTime.now(), d, p);
		a = appointmentRepository.save(a);
		Appointment a1 = new Appointment(LocalDateTime.now().plusDays(1), d, p);
		a1 = appointmentRepository.save(a1);
		Appointment a2 = new Appointment(LocalDateTime.now().plusDays(2), d, p);
		a2 = appointmentRepository.save(a2);
		
//		logger.debug(""+doctorRepository.findAll());
		List<Doctor> all = (List<Doctor>) doctorRepository.findAll();
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(all);
//		logger.debug(""+json);

		Medication m1 = new Medication();
		m1.setName("Crosin");
		m1.setType(MedicationType.Tablet);
		Medication m2 = new Medication();
		m2.setName("benadryl");
		m2.setType(MedicationType.Syrup);
		m1 = medicationRepository.save(m1);
		m2 = medicationRepository.save(m2);
		
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
		pe2.setBeforeFood_noon(true);
		pe2.setBeforeFood_night(true);
		pe2.setMedication(m2);
		pe2.setMorning(2);
		pe2.setNoon(0);
		pe2.setNight((float)0.5);
		pe2.setNotes("Work less");
		pe2.setNoOfDays(3);
		pe2.setUnit_morning(MedicationUnit.ML);
		pe2.setUnit_noon(MedicationUnit.ML);
		pe2.setUnit_night(MedicationUnit.ML);
		pe2.setPrescription(prescription);
		
//		Set<PrescriptionEntry> prescriptionEntries = new HashSet<>();
//		prescriptionEntries.add(pe1);
//		prescriptionEntries.add(pe2);
//		prescription.setPrescriptionEntries(prescriptionEntries);
		pe1 = prescriptionEntryRepository.save(pe1);
		pe2 = prescriptionEntryRepository.save(pe2);
		
		
		
		SettingsParams settingsParams = new SettingsParams();
		settingsParams.setParamName("ENABLED");
		settingsParams.setParamValue("FALSE");
		settingsParams = settingsParamsRepository.save(settingsParams);

		SettingsParams settingsParams1 = new SettingsParams();
		settingsParams1.setParamName("USERNAME");
		settingsParams1.setParamValue("success");
		settingsParams1 = settingsParamsRepository.save(settingsParams1);
		
		SettingsParams settingsParams11 = new SettingsParams();
		settingsParams11.setParamName("PASSWORD");
		settingsParams11.setParamValue("123456");
		settingsParams11 = settingsParamsRepository.save(settingsParams11);
		
		SettingsParams settingsParams12 = new SettingsParams();
		settingsParams12.setParamName("SENDER");
		settingsParams12.setParamValue("SPPURT");
		settingsParams12 = settingsParamsRepository.save(settingsParams12);
		
		SettingsParams settingsParams13 = new SettingsParams();
		settingsParams13.setParamName("URL");
		settingsParams13.setParamValue("http://bhashsms.com/api/sendmsg.php");
		settingsParams13 = settingsParamsRepository.save(settingsParams13);
		
		SettingsParams settingsParams14 = new SettingsParams();
		settingsParams14.setParamName("TYPE");
		settingsParams14.setParamValue("normal");
		settingsParams14 = settingsParamsRepository.save(settingsParams14);
		
		SettingsParams settingsParams15 = new SettingsParams();
		settingsParams15.setParamName("PRIORITY");
		settingsParams15.setParamValue("ndnd");
		settingsParams15 = settingsParamsRepository.save(settingsParams15);
		
		Settings settings = new Settings();
		settings.setType("GLOBAL");
		settings.setCategory("SMS");
		settings.getSettingsParams().add(settingsParams);
		settings.getSettingsParams().add(settingsParams1);
		settings.getSettingsParams().add(settingsParams11);
		settings.getSettingsParams().add(settingsParams12);
		settings.getSettingsParams().add(settingsParams13);
		settings.getSettingsParams().add(settingsParams14);
		settings.getSettingsParams().add(settingsParams15);
		settings = settingsRepository.save(settings);
		
		SettingsParams settingsParams2 = new SettingsParams();
		settingsParams2.setParamName("NAME");
		settingsParams2.setParamValue("Sree Balaji Dental Clinic");
		settingsParams2 = settingsParamsRepository.save(settingsParams2);
		
		SettingsParams settingsParams3 = new SettingsParams();
		settingsParams3.setParamName("ADDRESS");
		settingsParams3.setParamValue("No 14, 11th Street, Balaji Nagar, Adambakkam, Chennai, Tamil Nadu. PIN: 600088");
		settingsParams3 = settingsParamsRepository.save(settingsParams3);
		
		SettingsParams settingsParams4 = new SettingsParams();
		settingsParams4.setParamName("MOBILENO");
		settingsParams4.setParamValue("+91 44 43559921");
		settingsParams4 = settingsParamsRepository.save(settingsParams4);
		
		Settings settings1 = new Settings();
		settings1.setType("GLOBAL");
		settings1.setCategory("CLINIC");
		settings1.getSettingsParams().add(settingsParams2);
		settings1.getSettingsParams().add(settingsParams3);
		settings1.getSettingsParams().add(settingsParams4);
		settings1 = settingsRepository.save(settings1);
		
		logger.debug("All Settings "+settingsRepository.findAll());
	}
}
