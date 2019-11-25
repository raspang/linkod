package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.Barangay;
import com.dost12.ras.model.Code;
import com.dost12.ras.model.ReportCode;

public interface ReportCodeService {
	
	ReportCode findById(Integer id);
	
	ReportCode findByReportCode(Integer barangayId);
	
	void saveReportCode(ReportCode reportCode);
	
	void updateReportCode(ReportCode reportCode);
	
	void deleteReportCode(ReportCode reportCode);

	List<ReportCode> findAllReportCodes(); 


}