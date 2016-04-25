package com.training.hibernate.dao.impl;

import com.training.hibernate.model.PersonAudit;
import com.training.hibernate.dao.PersonAuditDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Transactional
@Repository
public class PersonAuditDaoImpl implements PersonAuditDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PersonAudit savePersonAudit(PersonAudit personAudit) {
		sessionFactory.getCurrentSession().save(personAudit);
		return personAudit;
	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonAudit> list(){
		List<PersonAudit> personAudits = (List<PersonAudit>) sessionFactory.getCurrentSession()
			.createCriteria(PersonAudit.class)
			.list();
		return personAudits;	
	}
}