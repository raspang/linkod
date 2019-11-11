package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Purok;

public interface PurokService {
	
	Purok findById(int id);
	
	void savePurok(Purok purok);
	
	void updatePurok(Purok purok);
	
	void deletePurok(Purok purok);

	List<Purok> findAllPuroks(); 


}