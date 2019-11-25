package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.Purok;


public interface PurokDao {

	Purok findById(int id);
	
	void save(Purok purok);
	
	void delete(Purok purok);
	
	List<Purok> findAllPuroks();

}

