package com.websystique.springmvc.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.service.BarangayService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class BarangayToMyBarangayConverter implements Converter<Object, Barangay>{

	static final Logger logger = LoggerFactory.getLogger(BarangayToMyBarangayConverter.class);
	
	@Autowired
	BarangayService barangayService;

	/**
	 * Gets Barangay by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Barangay convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Barangay barangay = barangayService.findById(id);
		logger.info("Barangay : {}", barangay);
		return barangay;
	}
	
}