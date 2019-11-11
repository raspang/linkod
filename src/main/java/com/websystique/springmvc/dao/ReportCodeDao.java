package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;
import com.websystique.springmvc.model.ReportCode;


public interface ReportCodeDao {

	ReportCode findById(Integer id);

	ReportCode findByReportCode(Integer barangayId);
	
	void save(ReportCode reportCode);
	
	void delete(ReportCode reportCode);
	
	List<ReportCode> findAllReportCodes();

}

