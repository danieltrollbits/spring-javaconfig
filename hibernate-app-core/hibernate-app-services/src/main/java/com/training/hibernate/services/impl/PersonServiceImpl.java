package com.training.hibernate.services;

import com.training.hibernate.model.*;
import com.training.hibernate.dto.*;
import com.training.hibernate.dao.PersonDao;
import com.training.hibernate.services.PersonService;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public List<PersonDto> getAllPersons() {
		return toDtos(personDao.getAllPersons());
	}

	@Override
	public PersonDto getPersonById(int id) {
		return toDto(personDao.getPersonById(id));
	}

	@Override
	public PersonDto savePerson(PersonDto personDto){
		return toDto(personDao.savePerson(toModel(personDto)));
	}

	@Override
	public PersonDto updatePerson(PersonDto personDto){
		return toDto(personDao.updatePerson(toModel(personDto)));
	}

	@Override
	public PersonDto deletePerson(int id){
		return toDto(personDao.deletePerson(id));
	}

	@Override
	public List<PersonDto> searchPerson(String lastName, String firstName, String middleName, String role) {
		return toDtos(personDao.searchPerson(lastName, firstName, middleName, role));
	}

	@Override
	public List<RoleDto> getRoles(){
		return rolesToDtos(personDao.getRoles());
	}

	public RoleDto roleToDto(Role role){
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getId());
		roleDto.setRole(role.getRole());
		return roleDto;
	}

	public List<RoleDto> rolesToDtos(List<Role> roles){
		List<RoleDto> roleDtos = new ArrayList<>();
		for (Role role : roles){
			roleDtos.add(roleToDto(role));
		}
		return roleDtos;
	}

	public PersonDto toDto(Person person){
		PersonDto personDto = new PersonDto();
		AddressDto addressDto = new AddressDto();
		List<ContactDto> contactDtos = new ArrayList<>();
		List<RoleDto> roleDtos = new ArrayList<>();
		try{
			BeanUtils.copyProperties(personDto,person);
			BeanUtils.copyProperties(addressDto,person.getAddress());
			for (Contact contact : person.getContacts()){
				ContactDto contactDto = new ContactDto();
				BeanUtils.copyProperties(contactDto,contact);
				contactDtos.add(contactDto);
			}
			
			for(Role role : person.getRoles()){
				RoleDto roleDto = new RoleDto();
				BeanUtils.copyProperties(roleDto,role);
				roleDtos.add(roleDto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		personDto.setAddressDto(addressDto);
		personDto.setContactDtos(contactDtos);
		personDto.setRoleDtos(roleDtos);
		return personDto;
	}

	public List<PersonDto> toDtos(List<Person> persons){
		List<PersonDto> personDtos = new ArrayList<>();
		for (Person person : persons){
			personDtos.add(toDto(person));	
		}
		return personDtos;
	}

	public Person toModel(PersonDto personDto){
		Person person = new Person();
		Address address = new Address();
		List<Contact> contacts = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		try{
			BeanUtils.copyProperties(person,personDto);
			BeanUtils.copyProperties(address,personDto.getAddressDto());
			for (ContactDto contactDto : personDto.getContactDtos()){
				Contact contact = new Contact();
				BeanUtils.copyProperties(contact,contactDto);
				contact.setPerson(person);
				contacts.add(contact);
			}
			for(RoleDto roleDto : personDto.getRoleDtos()){
				Role role = personDao.getRoleByName(roleDto.getRole());
				int id = role.getId();
				BeanUtils.copyProperties(role,roleDto);
				role.setId(id);
				roles.add(role);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		person.setAddress(address);
		person.setContacts(new LinkedHashSet(contacts));
		person.setRoles(new LinkedHashSet(roles));
		return person;	
	}

	public List<Person> toModels(List<PersonDto> personDtos){
		List<Person> persons = new ArrayList<>();
		for (PersonDto personDto : personDtos){
			persons.add(toModel(personDto));	
		}
		return persons;
	}

	@Override
	public ContactDto addContact(String contactType, String contactValue){
		ContactDto contactDto = new ContactDto();
		contactDto.setType(Type.valueOf(contactType.toUpperCase()));
		contactDto.setValue(contactValue);
		return contactDto;
	}

}