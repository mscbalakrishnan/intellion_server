package com.intellion.cms.domain.template;

import lombok.Data;

@Data
public class SmsTemplateData {
	private String userName;
	private String dateTime;
	private String hospitalName;
	private String hospitalPhone;
	private String doctorName;
	private String patientName;
}
