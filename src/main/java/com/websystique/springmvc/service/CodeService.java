package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;

public interface CodeService {
	
	Code findById(Integer id);
	
	void saveCode(Code code);
	
	void updateCode(Code code);
	
	void deleteCode(Code code);

	List<Code> findAllCodes(); 
	
	List<Code> findAllCodesChecked(); 
	
	//List<Code> findAllCodes(Barangay b);


}