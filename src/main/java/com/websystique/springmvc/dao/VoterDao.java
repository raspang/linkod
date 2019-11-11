package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Voter;


public interface VoterDao {

	Voter findById(Long id);
	
	void save(Voter voter);
	
	void delete(Voter voter);
	
	List<Voter> findAllVoters();

	List<Voter> findAllVoters(Long id1, Long id2);

}

