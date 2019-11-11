package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.BarangayDao;
import com.websystique.springmvc.dao.CodeDao;
import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;


@Service("codeService")
@Transactional
public class CodeServiceImpl implements CodeService{

	@Autowired
	private CodeDao dao;

	
	public Code findById(Integer id) {
		return dao.findById(id);
	}


	public void saveCode(Code code) {
		dao.save(code);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateCode(Code code) {
		Code entity =  dao.findById(code.getId());
		if(entity!=null){
			entity.setCode(code.getCode());
			//entity.setBarangay(code.getBarangay());
			entity.setStatus(code.getStatus());
		}
	}

	public List<Code> findAllCodes() {
		return dao.findAllCodes();
	}

	
	public List<Code> findAllCodesChecked() {
		return dao.findAllCodesChecked();
	}

	@Override
	public void deleteCode(Code code) {
		dao.delete(code);
		
	}






}
