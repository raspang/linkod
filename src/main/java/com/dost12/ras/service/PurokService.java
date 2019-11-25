package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.Barangay;
import com.dost12.ras.model.Purok;

public interface PurokService {
	
	Purok findById(int id);
	
	void savePurok(Purok purok);
	
	void updatePurok(Purok purok);
	
	void deletePurok(Purok purok);

	List<Purok> findAllPuroks(); 


}