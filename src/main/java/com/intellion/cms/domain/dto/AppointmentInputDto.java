package com.intellion.cms.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AppointmentInputDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private LocalDateTime time;
	private Long doctorId;
	private String patientId;
	private boolean smsToPatient;
	private boolean smsToDoctor;
}
