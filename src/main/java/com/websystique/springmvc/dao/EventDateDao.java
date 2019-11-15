package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.EventDate;


public interface EventDateDao {

	EventDate findById(Integer id);
	
	void save(EventDate eventDate);
	
	void delete(EventDate eventDate);
	
	List<EventDate> findAllEventDates();
	
	List<EventDate> findAllEnableEventDates();

}

