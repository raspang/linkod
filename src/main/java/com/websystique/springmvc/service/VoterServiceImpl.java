package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.VoterDao;
import com.websystique.springmvc.model.Voter;


@Service("voterService")
@Transactional
public class VoterServiceImpl implements VoterService{

	@Autowired
	private VoterDao dao;

	
	public Voter findById(Long id) {
		return dao.findById(id);
	}


	public void saveVoter(Voter code) {
		dao.save(code);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateVoter(Voter code) {
		Voter entity =  dao.findById(code.getId());
		if(entity!=null){
			entity.setCode(code.getCode());
			entity.setFirstName(code.getFirstName());
			entity.setMiddleName(code.getMiddleName());
			entity.setLastName(code.getLastName());
			entity.setCompany(code.getCompany());
			entity.setDesignation(code.getDesignation());
			entity.setContact(code.getContact());
			entity.setAge(code.getAge());
			entity.setGender(code.getGender());
			entity.setStatus(code.getStatus());
			entity.setBusiness(code.getBusiness());
			entity.setAttended(code.getAttended());

		}
	}

	public List<Voter> findAllVoters() {
		return dao.findAllVoters();
	}

	public List<Voter> findAllVoters(Long id1, Long id2) {
		return dao.findAllVoters(id1, id2);
	}


	@Override
	public void deleteVoter(Voter code) {
		dao.delete(code);
		
	}






}
