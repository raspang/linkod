package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.Barangay;
import com.dost12.ras.model.Code;
import com.dost12.ras.model.ReportCode;


public interface ReportCodeDao {

	ReportCode findById(Integer id);

	ReportCode findByReportCode(Integer barangayId);
	
	void save(ReportCode reportCode);
	
	void delete(ReportCode reportCode);
	
	List<ReportCode> findAllReportCodes();

}

