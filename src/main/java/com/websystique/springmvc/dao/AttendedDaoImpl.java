package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Attended;
import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.User;



@Repository("attendedDao")
public class AttendedDaoImpl extends AbstractDao<Long, Attended> implements AttendedDao {

	static final Logger logger = LoggerFactory.getLogger(AttendedDaoImpl.class);
	
	public Attended findById(Long id) {
		Attended attended = getByKey(id);
		return attended;
	}


	@SuppressWarnings("unchecked")
	public List<Attended> findAllAttendeds() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Attended> attends = (List<Attended>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return attends;
	}

	public void save(Attended attended) {
		persist(attended);
	}

	public void delete(Attended attended) {
/*		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();*/
		delete(attended);
	}



}
