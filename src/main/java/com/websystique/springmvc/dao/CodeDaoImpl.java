package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Code;



@Repository("codeDao")
public class CodeDaoImpl extends AbstractDao<Integer, Code> implements CodeDao {

	static final Logger logger = LoggerFactory.getLogger(CodeDaoImpl.class);
	
	public Code findById(Integer id) {
		Code code = getByKey(id);
		return code;
	}


	@SuppressWarnings("unchecked")
	public List<Code> findAllCodes() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("code"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Code> barangays = (List<Code>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}

	
	@SuppressWarnings("unchecked")
	public List<Code> findAllCodesChecked() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("code"));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		
		List<Code> barangays = (List<Code>) criteria.add(Restrictions.eq("status", false)).list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}
	public void save(Code code) {
		persist(code);
		
	}
	

	public void deleteCode(Code code) {
		delete(code);
	}


}
