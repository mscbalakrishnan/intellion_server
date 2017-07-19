package com.example.demo.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.domain.Category;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.dto.DoctorDto;

public class ControllerUtil {
	private static final Logger logger = LoggerFactory.getLogger(ControllerUtil.class);

	public static List<DoctorDto> toDto(List<Doctor> doctors) {
		logger.debug("" + doctors);
		List<DoctorDto> toReturn = new ArrayList<>();
		for (Doctor doctor : doctors) {
			DoctorDto ddto = new DoctorDto(doctor);
			Set<CategoryDto> catDtoList = new HashSet<>();
			for (Category category : doctor.getCategories()) {
				CategoryDto cdto = new CategoryDto(category);
				catDtoList.add(cdto);
			}
			ddto.setCategories(catDtoList);
			toReturn.add(ddto);
		}
		logger.debug("" + toReturn);
		return toReturn;
	}
}
