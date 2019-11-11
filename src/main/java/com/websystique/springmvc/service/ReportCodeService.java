package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;
import com.websystique.springmvc.model.ReportCode;

public interface ReportCodeService {
	
	ReportCode findById(Integer id);
	
	ReportCode findByReportCode(Integer barangayId);
	
	void saveReportCode(ReportCode reportCode);
	
	void updateReportCode(ReportCode reportCode);
	
	void deleteReportCode(ReportCode reportCode);

	List<ReportCode> findAllReportCodes(); 


}