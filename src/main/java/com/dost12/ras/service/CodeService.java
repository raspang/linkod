package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.Barangay;
import com.dost12.ras.model.Code;

public interface CodeService {
	
	Code findById(Integer id);
	
	void saveCode(Code code);
	
	void updateCode(Code code);
	
	void deleteCode(Code code);

	List<Code> findAllCodes(); 
	
	List<Code> findAllCodesChecked(); 
	
	//List<Code> findAllCodes(Barangay b);


}