package com.training.hibernate.dao.impl;

import com.training.hibernate.model.Person;
import com.training.hibernate.model.Address;
import com.training.hibernate.model.Contact;
import com.training.hibernate.model.Role;
import com.training.hibernate.dao.PersonDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class PersonDaoImpl implements PersonDao{

	private SessionFactory sessionFactory;

	public PersonDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Person> getAllPersons(){
		@SuppressWarnings("unchecked")
		List<Person> persons = (List<Person>) sessionFactory.getCurrentSession()
			.createCriteria(Person.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
		return persons;
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		Person person = (Person) sessionFactory.getCurrentSession()
			.createCriteria(Person.class)
			.add(Restrictions.eq("id",id))
			.uniqueResult();
		return person;
	}

	@Override
	@Transactional
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
		@SuppressWarnings("unchecked")
		List<Person> persons = (List<Person>) criteria.list();
		return persons;
	}

	@Override
	@Transactional
	public Person saveOrUpdatePerson(Person person) {
		if(person.getId() == 0){
			sessionFactory.getCurrentSession().save(person);
		}
		else{
			sessionFactory.getCurrentSession().update(person);
		}
		return person;
	}

	public Person deletePerson(int id){
		Person person = (Person)sessionFactory.getCurrentSession().get(Person.class, id);
		sessionFactory.getCurrentSession().delete(person);
		return person;
	}

	public Role getRoleByName(String name){
		Role role = (Role) sessionFactory.getCurrentSession().createCriteria(Role.class)
			.add(Restrictions.eq("role",name)).uniqueResult();
		return role;
	}

	public List<Role> getRoles(){
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) sessionFactory.getCurrentSession()
			.createCriteria(Role.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return roles;
	}
}