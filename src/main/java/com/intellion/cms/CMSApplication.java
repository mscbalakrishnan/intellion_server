package com.intellion.cms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.intellion.cms.domain.Label;
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
import com.intellion.cms.repository.LabelRepository;
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
	
	@Autowired
    private LabelRepository labelRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CMSApplication.class, args);
	}

	public void run1(String... arg0) throws Exception {
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
		//d.setCategories(categories);
		d = doctorRepository.save(d);
		d1.setName("Guru");
		d1.setTitle(Title.Dr);
		d1.setEmail("xyz@abc.com");
		//d1.setCategories(categories);
		d1.setMobile1("9971415876");
		d1 = doctorRepository.save(d1);
		
		Set<Doctor> doctors = new HashSet<>();
		doctors.add(d);
		doctors.add(d1);
		
		//dentist.setDoctors(doctors);
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
		//p.setBloodGroup(BloodGroup.BPositive);
		p.setDob(LocalDate.of(1989, 1, 15));
		p.getAddressList().add(address1);
		p = patientRepository.save(p);
		
		Patient p1 = new Patient();
		p1.setName("pandian Babu");
		p1.setMobileNumber1("9600194696");
		p1.setTitle(Title.Mr);
		//p1.setBloodGroup(BloodGroup.BPositive);
		p1.setDob(LocalDate.of(1989, 1, 15));
		p1.getAddressList().add(address1);
		p1 = patientRepository.save(p1);
		
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
		
		
		Label l = new Label();
		l.setLabelname("POOR");
		List<Patient> patList = new ArrayList<Patient>();
		patList.add(p); patList.add(p1);
		//l.setPatientList(patList);
		l = labelRepository.save(l);
		
		Label l1 = new Label();
		l1.setLabelname("RICH");
		
		patList.add(p); patList.add(p1);
		l1.setPatientList(patList);
		l1 = labelRepository.save(l1);
		
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
	}
	public void run(String... arg0) throws Exception {
		
		
		List<Settings> settingsListByCat =  (List<Settings>)this.settingsRepository.findByCategory("sms");
		
		if(null != settingsListByCat && settingsListByCat.size() > 0){
			logger.debug("All Settings are already there");
		}else{
			SettingsParams smsGlobalSwitch = new SettingsParams();
			smsGlobalSwitch.setParamName("sms_global_switch");
			smsGlobalSwitch.setParamValue("false");
			smsGlobalSwitch = settingsParamsRepository.save(smsGlobalSwitch);

			SettingsParams smsUserName = new SettingsParams();
			smsUserName.setParamName("username");
			smsUserName.setParamValue("success");
			smsUserName = settingsParamsRepository.save(smsUserName);
			
			SettingsParams smsPassword = new SettingsParams();
			smsPassword.setParamName("password");
			smsPassword.setParamValue("123456");
			smsPassword = settingsParamsRepository.save(smsPassword);
			
			SettingsParams smsSender = new SettingsParams();
			smsSender.setParamName("sender");
			smsSender.setParamValue("SPPURT");
			smsSender = settingsParamsRepository.save(smsSender);
			
			SettingsParams smsUrl = new SettingsParams();
			smsUrl.setParamName("url");
			smsUrl.setParamValue("http://bhashsms.com/api/sendmsg.php");
			smsUrl = settingsParamsRepository.save(smsUrl);
			
			SettingsParams smsType = new SettingsParams();
			smsType.setParamName("type");
			smsType.setParamValue("normal");
			smsType = settingsParamsRepository.save(smsType);
			
			SettingsParams smsPriority = new SettingsParams();
			smsPriority.setParamName("priority");
			smsPriority.setParamValue("ndnd");
			smsPriority = settingsParamsRepository.save(smsPriority);
			
			SettingsParams smsBirthdayAlarm = new SettingsParams();
			smsBirthdayAlarm.setParamName("sms_birthday_alarm");
			smsBirthdayAlarm.setParamValue("false");
			smsBirthdayAlarm = settingsParamsRepository.save(smsBirthdayAlarm);
			
			SettingsParams smsPeriodicRemainder = new SettingsParams();
			smsPeriodicRemainder.setParamName("sms_periodic_reminder");
			smsPeriodicRemainder.setParamValue("false");
			smsPeriodicRemainder = settingsParamsRepository.save(smsPeriodicRemainder);
			
			Settings settings = new Settings();
			settings.setCategory("sms");
			settings.getSettingsParams().add(smsGlobalSwitch);
			settings.getSettingsParams().add(smsUserName);
			settings.getSettingsParams().add(smsPassword);
			settings.getSettingsParams().add(smsSender);
			settings.getSettingsParams().add(smsUrl);
			settings.getSettingsParams().add(smsType);
			settings.getSettingsParams().add(smsPriority);
			settings.getSettingsParams().add(smsBirthdayAlarm);
			settings.getSettingsParams().add(smsPeriodicRemainder);
			settings = settingsRepository.save(settings);
			
			
			
			SettingsParams settingsParams2 = new SettingsParams();
			settingsParams2.setParamName("clinicName");
//			settingsParams2.setParamValue("Sree Balaji Dental Clinic");
			settingsParams2.setParamValue("");
			settingsParams2 = settingsParamsRepository.save(settingsParams2);
			
			SettingsParams settingsParams21 = new SettingsParams();
			settingsParams21.setParamName("addressLine1");
//			settingsParams21.setParamValue("No 14, 11th Street, Balaji Nagar, Adambakkam, Chennai, Tamil Nadu. PIN: 600088");
			settingsParams21.setParamValue("");
			settingsParams21 = settingsParamsRepository.save(settingsParams21);
			
			SettingsParams settingsParams22 = new SettingsParams();
			settingsParams22.setParamName("addressLine2");
			settingsParams22.setParamValue("");
			settingsParams22 = settingsParamsRepository.save(settingsParams22);
			
			SettingsParams settingsParams23 = new SettingsParams();
			settingsParams23.setParamName("mobile");
//			settingsParams23.setParamValue("+91 44 43559921");
			settingsParams23.setParamValue("");
			settingsParams23 = settingsParamsRepository.save(settingsParams23);
			
			SettingsParams settingsParams24 = new SettingsParams();
			settingsParams24.setParamName("area");
			settingsParams24.setParamValue("");
			settingsParams24 = settingsParamsRepository.save(settingsParams24);
			
			SettingsParams settingsParams25 = new SettingsParams();
			settingsParams25.setParamName("city");
			settingsParams25.setParamValue("");
			settingsParams25 = settingsParamsRepository.save(settingsParams25);
			
			SettingsParams settingsParams26 = new SettingsParams();
			settingsParams26.setParamName("state");
			settingsParams26.setParamValue("");
			settingsParams26 = settingsParamsRepository.save(settingsParams26);
			
			SettingsParams settingsParams27 = new SettingsParams();
			settingsParams27.setParamName("pincode");
			settingsParams27.setParamValue("");
			settingsParams27 = settingsParamsRepository.save(settingsParams27);
			
			SettingsParams settingsParams28 = new SettingsParams();
			settingsParams28.setParamName("email");
			settingsParams28.setParamValue("");
			settingsParams28 = settingsParamsRepository.save(settingsParams28);
			
			SettingsParams settingsParams29 = new SettingsParams();
			settingsParams29.setParamName("landline");
			settingsParams29.setParamValue("");
			settingsParams29 = settingsParamsRepository.save(settingsParams29);
			
			SettingsParams settingsParams30 = new SettingsParams();
			settingsParams30.setParamName("website");
			settingsParams30.setParamValue("");
			settingsParams30 = settingsParamsRepository.save(settingsParams30);

			Settings settings1 = new Settings();
			settings1.setCategory("clinic");
			settings1.getSettingsParams().add(settingsParams2);
			settings1.getSettingsParams().add(settingsParams21);
			settings1.getSettingsParams().add(settingsParams22);
			settings1.getSettingsParams().add(settingsParams23);
			settings1.getSettingsParams().add(settingsParams24);
			settings1.getSettingsParams().add(settingsParams25);
			settings1.getSettingsParams().add(settingsParams26);
			settings1.getSettingsParams().add(settingsParams27);
			settings1.getSettingsParams().add(settingsParams28);
			settings1.getSettingsParams().add(settingsParams29);
			settings1.getSettingsParams().add(settingsParams30);
			settings1 = settingsRepository.save(settings1);
			
			logger.debug("All Settings "+settingsRepository.findAll());
		}
		
		
	}
}
