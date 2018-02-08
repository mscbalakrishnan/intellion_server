package com.intellion.cms.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.intellion.cms.domain.SmsDetails;
import com.intellion.cms.domain.SmsStatus;
import com.intellion.cms.repository.SmsDetailsRepository;
import com.intellion.cms.service.SmsDetailsService;

@Service("smsService")
public class SmsDetailsServiceImpl implements SmsDetailsService {
	private final SmsDetailsRepository smsDetailsRepository;
	
	public SmsDetailsServiceImpl(SmsDetailsRepository  smsDetailsRepository) {
		super();
		this.smsDetailsRepository = smsDetailsRepository;
	}
	
	@Override
	public SmsDetails save(SmsDetails smsDetails) {
		return smsDetailsRepository.save(smsDetails);
	}
	@Override
	public Iterable<SmsDetails> getPendingSms(){
		return smsDetailsRepository.findByDateBeforeAndStatusNotAndRetryCountLessThan(Instant.now().toEpochMilli(), SmsStatus.SUCCESS.name(),5);
	}
	
	
}
