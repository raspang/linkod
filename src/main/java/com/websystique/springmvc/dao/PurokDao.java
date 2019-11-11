package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Purok;


public interface PurokDao {

	Purok findById(int id);
	
	void save(Purok purok);
	
	void delete(Purok purok);
	
	List<Purok> findAllPuroks();

}

