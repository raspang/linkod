package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Barangay;


public interface BarangayDao {

	Barangay findById(int id);
	
	void save(Barangay barangay);
	
	void delete(Barangay barangay);
	
	List<Barangay> findAllBarangays();

}

