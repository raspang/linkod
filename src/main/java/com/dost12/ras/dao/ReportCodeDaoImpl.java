package com.dost12.ras.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dost12.ras.model.Barangay;
import com.dost12.ras.model.Code;
import com.dost12.ras.model.ReportCode;
import com.dost12.ras.model.User;



@Repository("reportCodeDao")
public class ReportCodeDaoImpl extends AbstractDao<Integer, ReportCode> implements ReportCodeDao {

	static final Logger logger = LoggerFactory.getLogger(ReportCodeDaoImpl.class);
	
	public ReportCode findById(Integer id) {
		ReportCode Barangay = getByKey(id);
		return Barangay;
	}


	@SuppressWarnings("unchecked")
	public List<ReportCode> findAllReportCodes() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<ReportCode> barangays = (List<ReportCode>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}

	public void save(ReportCode reportCode) {
		persist(reportCode);
		
	}
	

	public void delete(ReportCode reportCode) {
/*		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();*/
		delete(reportCode);
	}

	public ReportCode findByReportCode(Integer barangayId) {
		logger.info("SSO : {}", barangayId);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("barangayId", barangayId));
		ReportCode reportCode = (ReportCode)crit.uniqueResult();
		return reportCode;
	}

}
