package com.intellion.cms.service.impl;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellion.cms.domain.Label;
import com.intellion.cms.repository.LabelRepository;
import com.intellion.cms.service.LabelService;
@Service("labelService")
public class LabelServiceImpl implements LabelService {
	private static final Logger logger = LoggerFactory.getLogger(LabelServiceImpl.class);
	private final LabelRepository labelRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }
    
	

	@Override
	public Label save(Label label) {
		Label l = null;
		try {
			l = labelRepository.save(label);
		} catch (Exception e1) {
			Exception e = (Exception)e1.getCause();
			e = (Exception)e.getCause();
			if (e instanceof ConstraintViolationException){
				ConstraintViolationException e2 = (ConstraintViolationException) e;
				String errMsg = "";
				for (ConstraintViolation cv : e2.getConstraintViolations()){
					errMsg += cv.getMessageTemplate();
				}
				throw new IllegalArgumentException(errMsg);
			} else {
				throw e1;
			}
			
		}
		logger.debug("Saved Object is {}",l);
		return l;
	}

	@Override
	public Iterable<Label> findAll() {
		return labelRepository.findAll();
	}

	@Override
	public Label findOne(long id) {
		return labelRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		labelRepository.delete(id);
	}
	

}
