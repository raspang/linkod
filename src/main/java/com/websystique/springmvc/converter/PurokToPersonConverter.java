package com.websystique.springmvc.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Purok;
import com.websystique.springmvc.service.BarangayService;
import com.websystique.springmvc.service.PurokService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class PurokToPersonConverter implements Converter<Object, Purok>{

	static final Logger logger = LoggerFactory.getLogger(PurokToPersonConverter.class);
	
	@Autowired
	PurokService purokService;

	/**
	 * Gets Purok by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Purok convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Purok purok = purokService.findById(id);
		logger.info("Purok : {}", purok);
		return purok;
	}
	
}