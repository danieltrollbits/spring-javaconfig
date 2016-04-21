package com.training.hibernate.services;

import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.dto.RoleDto;
import com.training.hibernate.dto.ContactDto;
import java.util.List;

public interface PersonService {

	public List<PersonDto> getAllPersons();

	public PersonDto getPersonById(int id);

	public PersonDto saveOrUpdatePerson(PersonDto personDto);

	public PersonDto deletePerson(int id);

	public List<PersonDto> searchPerson(String lastName, String firstName, String middleName, String role);

	public List<RoleDto> getRoles();

	public ContactDto addContact(String contactType, String contactValue);

}