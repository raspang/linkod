package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Attended;

public interface AttendedService {
	
	Attended findById(Long id);
	
	void saveAttended(Attended attended);
	
	void updateAttended(Attended attended);
	
	void deleteAttended(Attended attended);

	List<Attended> findAllAttends(); 


}