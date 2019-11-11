package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;


public interface CodeDao {

	Code findById(Integer id);
	
	void save(Code code);
	
	void delete(Code barangay);
	
	List<Code> findAllCodes();
	
	List<Code> findAllCodesChecked();

}

