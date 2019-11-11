package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.PurokDao;
import com.websystique.springmvc.model.Purok;


@Service("purokService")
@Transactional
public class PurokServiceImpl implements PurokService{

	@Autowired
	private PurokDao dao;

	
	public Purok findById(int id) {
		return dao.findById(id);
	}


	public void savePurok(Purok purok) {
		dao.save(purok);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updatePurok(Purok purok) {
		Purok entity =  dao.findById(purok.getId());
		if(entity!=null){
			entity.setName(purok.getName());
//			entity.setPuroks(entity.getPuroks());

		}
	}

	public List<Purok> findAllPuroks() {
		return dao.findAllPuroks();
	}


	@Override
	public void deletePurok(Purok purok) {
		dao.delete(purok);
		
	}




}
