package com.dost12.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dost12.ras.dao.BarangayDao;
import com.dost12.ras.model.Barangay;


@Service("barangayService")
@Transactional
public class BarangayServiceImpl implements BarangayService{

	@Autowired
	private BarangayDao dao;

	
	public Barangay findById(int id) {
		return dao.findById(id);
	}


	public void saveBarangay(Barangay barangay) {
		dao.save(barangay);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateBarangay(Barangay barangay) {
		Barangay entity =  dao.findById(barangay.getId());
		if(entity!=null){
			entity.setName(barangay.getName());
//			entity.setPuroks(entity.getPuroks());
			entity.setCountGenerated(barangay.getCountGenerated());

		}
	}

	public List<Barangay> findAllBarangays() {
		return dao.findAllBarangays();
	}


	@Override
	public void deleteBarangay(Barangay barangay) {
		dao.delete(barangay);;
		
	}


}
