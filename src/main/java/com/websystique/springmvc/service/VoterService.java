package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;
import com.websystique.springmvc.model.Voter;

public interface VoterService {
	
	Voter findById(Long id);
	
	void saveVoter(Voter code);
	
	void updateVoter(Voter code);
	
	void deleteVoter(Voter code);

	List<Voter> findAllVoters(); 

	List<Voter> findAllVoters(Long id1, Long id2);

}