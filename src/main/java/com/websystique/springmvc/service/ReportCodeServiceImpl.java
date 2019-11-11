package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.BarangayDao;
import com.websystique.springmvc.dao.CodeDao;
import com.websystique.springmvc.dao.ReportCodeDao;
import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;
import com.websystique.springmvc.model.ReportCode;


@Service("reportCodeService")
@Transactional
public class ReportCodeServiceImpl implements ReportCodeService{

	@Autowired
	private ReportCodeDao dao;

	
	public ReportCode findById(Integer id) {
		return dao.findById(id);
	}


	public void saveReportCode(ReportCode reportCode) {
		dao.save(reportCode);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateReportCode(ReportCode reportCode) {
		ReportCode entity =  dao.findById(reportCode.getId());
		if(entity!=null){
			entity.setBarangayId(reportCode.getBarangayId());
			entity.setCount(reportCode.getCount());
		}
	}

	public List<ReportCode> findAllReportCodes() {
		return dao.findAllReportCodes();
	}


	@Override
	public void deleteReportCode(ReportCode code) {
		dao.delete(code);;
		
	}


	@Override
	public ReportCode findByReportCode(Integer barangayId) {
		// TODO Auto-generated method stub
		return dao.findByReportCode(barangayId);
	}






}
