package com.intellion.cms.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intellion.cms.domain.Appointment;
import com.intellion.cms.domain.Category;
import com.intellion.cms.domain.Doctor;
import com.intellion.cms.domain.dto.AppointmentDto;
import com.intellion.cms.domain.dto.CategoryDto;
import com.intellion.cms.domain.dto.DoctorDto;

public class ControllerUtil {
	private static final Logger logger = LoggerFactory.getLogger(ControllerUtil.class);

	public static List<DoctorDto> toDto(List<Doctor> doctors) {
		List<DoctorDto> toReturn = new ArrayList<>();
		for (Doctor doctor : doctors) {
			DoctorDto ddto = new DoctorDto(doctor);

			Set<AppointmentDto> appointmentDtos = new HashSet<>();
			for (Appointment appointment:doctor.getAppointments()){
				appointmentDtos.add(new AppointmentDto(appointment));
			}
			ddto.setAppointments(appointmentDtos);
			toReturn.add(ddto);
		}
		return toReturn;
	}
}
