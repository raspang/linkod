package com.dost12.ras.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dost12.ras.model.Barangay;
import com.dost12.ras.model.User;



@Repository("barangayDao")
public class BarangayDaoImpl extends AbstractDao<Integer, Barangay> implements BarangayDao {

	static final Logger logger = LoggerFactory.getLogger(BarangayDaoImpl.class);
	
	public Barangay findById(int id) {
		Barangay Barangay = getByKey(id);
		return Barangay;
	}


	@SuppressWarnings("unchecked")
	public List<Barangay> findAllBarangays() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Barangay> barangays = (List<Barangay>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}

	public void save(Barangay barangay) {
		persist(barangay);
	}

	public void delete(Barangay barangay) {
/*		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();*/
		delete(barangay);
	}



}
