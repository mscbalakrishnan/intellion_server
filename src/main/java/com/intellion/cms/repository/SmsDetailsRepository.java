package com.intellion.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intellion.cms.domain.SmsDetails;

public interface SmsDetailsRepository extends JpaRepository<SmsDetails, Long> {	
	List<SmsDetails> findByDateBeforeAndStatusNotAndRetryCountGreaterThan(long currentDate,String status,int retryCount);
	List<SmsDetails> findByNameContainingAndStatusNotLike(String name, String status);
	
}
