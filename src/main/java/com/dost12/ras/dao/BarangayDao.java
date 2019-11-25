package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.Barangay;


public interface BarangayDao {

	Barangay findById(int id);
	
	void save(Barangay barangay);
	
	void delete(Barangay barangay);
	
	List<Barangay> findAllBarangays();

}

