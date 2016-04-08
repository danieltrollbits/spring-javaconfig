package com.training.hibernate.dao;

import com.training.hibernate.model.Person;
import com.training.hibernate.model.Role;
import java.util.List;

public interface PersonDao {

	public List<Person> getAllPersons();

	public Person getPersonById(int id);

	public List<Person> searchPerson(String lastName, String firstName, String middleName, String role);

	public Person saveOrUpdatePerson(Person person);

	public Person deletePerson(int id);

	public Role getRoleByName(String name);

	public List<Role> getRoles();

}