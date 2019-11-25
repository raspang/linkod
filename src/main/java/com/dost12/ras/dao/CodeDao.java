package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.Barangay;
import com.dost12.ras.model.Code;


public interface CodeDao {

	Code findById(Integer id);
	
	void save(Code code);
	
	void delete(Code barangay);
	
	List<Code> findAllCodes();
	
	List<Code> findAllCodesChecked();

}

