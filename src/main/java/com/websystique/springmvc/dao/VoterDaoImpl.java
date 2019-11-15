package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Voter;



@Repository("voterDao")
public class VoterDaoImpl extends AbstractDao<Long, Voter> implements VoterDao {

	static final Logger logger = LoggerFactory.getLogger(VoterDaoImpl.class);
	
	public Voter findById(Long id) {
		Voter code = getByKey(id);
		return code;
	}


	@SuppressWarnings("unchecked")
	public List<Voter> findAllVoters() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("updated_At")).addOrder(Order.desc("id"));;
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Voter> barangays = (List<Voter>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}

	@SuppressWarnings("unchecked")
	public List<Voter> findAllVoters(Long id1, Long id2) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.add(Restrictions.between("id", id1, id2));
		
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Voter> barangays = (List<Voter>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}
	
	public void save(Voter voter) {
		persist(voter);
		
	}
	

	public void deleteCode(Voter voter) {
		delete(voter);
	}


}
