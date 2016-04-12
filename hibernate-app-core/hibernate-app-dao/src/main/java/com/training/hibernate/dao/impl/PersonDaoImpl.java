package com.training.hibernate.dao.impl;

import com.training.hibernate.model.Person;
import com.training.hibernate.model.Address;
import com.training.hibernate.model.Contact;
import com.training.hibernate.model.Role;
import com.training.hibernate.dao.PersonDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class PersonDaoImpl implements PersonDao{

	@Autowired
	private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPersons(){
		List<Person> persons = (List<Person>) sessionFactory.getCurrentSession()
			.createCriteria(Person.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
		return persons;
	}

	@Override
	public Person getPersonById(int id) {
		Person person = (Person) sessionFactory.getCurrentSession()
			.createCriteria(Person.class)
			.add(Restrictions.eq("id",id))
			.uniqueResult();
		return person;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> searchPerson(String lastName, String firstName, String middleName, String role) {
		Criteria criteria = sessionFactory.getCurrentSession()
			.createCriteria(Person.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (!lastName.isEmpty()){
			criteria.add(Restrictions.eq("lastName",lastName));	
		}
		if(!firstName.isEmpty()){
			criteria.add(Restrictions.eq("firstName",firstName));	
		}
		if(!middleName.isEmpty()){
			criteria.add(Restrictions.eq("middleName",middleName));	
		}
		/*if (!role.isEmpty()){
			criteria.createAlias("roles","role");
			criteria.add(Restrictions.in("role.role",role));	
		}*/
		List<Person> persons = (List<Person>) criteria.list();
		return persons;
	}

	@Override
	public Person saveOrUpdatePerson(Person person) {
		if(person.getId() == 0){
			sessionFactory.getCurrentSession().save(person);
		}
		else{
			sessionFactory.getCurrentSession().update(person);
		}
		return person;
	}

	@Override
	public Person deletePerson(int id){
		Person person = (Person) sessionFactory.getCurrentSession().get(Person.class, id);
		sessionFactory.getCurrentSession().delete(person);
		return person;
	}

	@Override
	public Role getRoleByName(String name){
		Role role = (Role) sessionFactory.getCurrentSession().createCriteria(Role.class)
			.add(Restrictions.eq("role",name)).uniqueResult();
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles(){
		List<Role> roles = (List<Role>) sessionFactory.getCurrentSession().createCriteria(Role.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return roles;
	}
}