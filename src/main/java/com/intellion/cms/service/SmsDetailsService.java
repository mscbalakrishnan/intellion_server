package com.intellion.cms.service;

import com.intellion.cms.domain.SmsDetails;

public interface SmsDetailsService {
	SmsDetails save(SmsDetails smsDetails);
	Iterable<SmsDetails> getPendingSms();
}
