package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.Barangay;

public interface BarangayService {
	
	Barangay findById(int id);
	
	void saveBarangay(Barangay barangay);
	
	void updateBarangay(Barangay barangay);
	
	void deleteBarangay(Barangay barangay);

	List<Barangay> findAllBarangays(); 


}