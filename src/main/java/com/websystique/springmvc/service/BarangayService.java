package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Barangay;

public interface BarangayService {
	
	Barangay findById(int id);
	
	void saveBarangay(Barangay barangay);
	
	void updateBarangay(Barangay barangay);
	
	void deleteBarangay(Barangay barangay);

	List<Barangay> findAllBarangays(); 


}