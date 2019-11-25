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
import com.dost12.ras.model.Purok;
import com.dost12.ras.model.User;



@Repository("purokDao")
public class PurokDaoImpl extends AbstractDao<Integer, Purok> implements PurokDao {

	static final Logger logger = LoggerFactory.getLogger(PurokDaoImpl.class);
	
	public Purok findById(int id) {
		Purok purok = getByKey(id);
		return purok;
	}


	@SuppressWarnings("unchecked")
	public List<Purok> findAllPuroks() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Purok> puroks = (List<Purok>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return puroks;
	}

	public void save(Purok purok) {
		persist(purok);
	}

	public void delete(Purok purok) {
/*		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();*/
		delete(purok);
	}



}
