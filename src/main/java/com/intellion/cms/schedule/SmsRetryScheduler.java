package com.intellion.cms.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SmsRetryScheduler {
	@Scheduled(cron="0 0 10-18 * * *")	
    public void sendSms(){
		
	}
}
