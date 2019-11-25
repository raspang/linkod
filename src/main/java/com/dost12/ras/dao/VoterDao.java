package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.Participant;


public interface VoterDao {

	Participant findById(Long id);
	
	void save(Participant voter);
	
	void delete(Participant voter);
	
	List<Participant> findAllVoters();

	List<Participant> findAllVoters(Long id1, Long id2);

}

