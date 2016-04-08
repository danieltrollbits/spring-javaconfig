package com.training.hibernate.services;

import com.training.hibernate.model.*;
import com.training.hibernate.dto.*;
import com.training.hibernate.dao.PersonDao;
import com.training.hibernate.services.PersonService;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.*;
import java.lang.NumberFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	public List<PersonDto> getAllPersons() {
		return toDtos(personDao.getAllPersons());
	}

	public PersonDto getPersonById(int id) {
		return toDto(personDao.getPersonById(id));
	}

	public PersonDto saveOrUpdatePerson(PersonDto personDto){
		return toDto(personDao.saveOrUpdatePerson(toModel(personDto)));
	}

	public PersonDto deletePerson(int id){
		return toDto(personDao.deletePerson(id));
	}

	public List<PersonDto> searchPerson(String lastName, String firstName, String middleName, String role) {
		return toDtos(personDao.searchPerson(lastName, firstName, middleName, role));
	}

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
		Set<ContactDto> contactDtos = new HashSet<>();
		Set<RoleDto> roleDtos = new HashSet<>();
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
		Set<Contact> contacts = new HashSet<>();
		Set<Role> roles = new HashSet<>();
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
		person.setContacts(contacts);
		person.setRoles(roles);
		return person;	
	}

	public List<Person> toModels(List<PersonDto> personDtos){
		List<Person> persons = new ArrayList<>();
		for (PersonDto personDto : personDtos){
			persons.add(toModel(personDto));	
		}
		return persons;
	}

	public PersonDto createPersonDto(String id, String firstName, String middleName, String lastName, String gender, String birthdate,
		String employed, String gwa, String street, String houseNo, String barangay, String subdivision,
		String city, String zipCode, String[] contactTypeList, String[] contactValueList, String[] contactId,
		String[] roleList, String[] savedContactValueList, String[] savedContactTypeList){
		
		PersonDto personDto = new PersonDto();
		if(!id.isEmpty()){
			if(id != "0"){
				personDto.setId(Integer.parseInt(id));	
			}
		}
		if(!birthdate.isEmpty()){
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			Date date = null;
			try{
				date = df.parse(birthdate);
				personDto.setBirthdate(date);
			}catch(ParseException | NumberFormatException e){
				e.printStackTrace();
			}
		}
		personDto.setFirstName(firstName);
		personDto.setMiddleName(middleName);
		personDto.setLastName(lastName);
		if(gender != null){
			personDto.setGender(Gender.valueOf(gender.toUpperCase()));
		}
		if(employed != null){
			if(employed.equals("yes")){
				personDto.setEmployed(true);
			}
			else{
				personDto.setEmployed(false);
			}	
		}
		if(!gwa.isEmpty()){
			if(gwa.matches("[1-9]\\d*(\\.\\d+)?$")){
				personDto.setGwa(Float.parseFloat(gwa));	
			}
		}
		Set<RoleDto> roleDtos = new HashSet<>();
		if(roleList != null){
			for(String role : roleList){
				RoleDto roleDto = new RoleDto(role);
				roleDtos.add(roleDto);
			}
			personDto.setRoleDtos(roleDtos);
		}
		
		//address
		AddressDto addressDto = new AddressDto();
		addressDto.setStreet(street);
		addressDto.setBarangay(barangay);
		addressDto.setSubdivision(subdivision);
		addressDto.setCity(city);
		addressDto.setZipCode(zipCode);
		if(!houseNo.isEmpty()){
			if(houseNo.matches("\\d+")){
				addressDto.setHouseNo(Integer.parseInt(houseNo));	
			}	
		}
		personDto.setAddressDto(addressDto);
		Set<ContactDto> contactDtos = new HashSet<>();

		if(savedContactTypeList !=null && savedContactValueList != null){
			for(int i=0; i<savedContactTypeList.length; i++){
				ContactDto contactDto = new ContactDto();
				contactDto.setId(Integer.parseInt(contactId[i]));
				contactDto.setType(Type.valueOf(savedContactTypeList[i].toUpperCase()));
				contactDto.setValue(savedContactValueList[i]);
				contactDtos.add(contactDto);
			}
		}

		if(contactTypeList != null && contactValueList.length > 0){
			for (int i=0; i<contactTypeList.length; i++){
				ContactDto contactDto = new ContactDto();
				contactDto.setType(Type.valueOf(contactTypeList[i].toUpperCase()));
				contactDto.setValue(contactValueList[i]);
				contactDtos.add(contactDto);
			}
		}
		personDto.setContactDtos(contactDtos);
		return personDto;
	}

	public boolean isRequired(String firstName, String middleName, String lastName, String gender, String employed,
		String street, String barangay, String subdivision, String city, String zipCode,
		String[] contactTypeList, String[] contactValueList, String[] roleList){

		if(firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || gender == null ||
			street.isEmpty() || barangay.isEmpty() || city.isEmpty() || zipCode.isEmpty() || roleList == null){
			return false;
		}
		else{
			return true;
		}
	}

	public boolean isDate(String strDate){
		boolean isValid = false;
		if(!strDate.isEmpty()){
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			Date date = null;
			try{
				date = df.parse(strDate);
				isValid = true;
			}catch(ParseException|NumberFormatException e){
				e.printStackTrace();
				isValid = false;
			}
		}
		else{
			isValid = false;
		}
		return isValid;
	}

	public boolean isNumber(String strNo){
		if(strNo.matches("\\d+")){
			return true; 
		}
		else{
			return false;
		}
	}

	public boolean isDecimal(String strDecimal){
		if(strDecimal.matches("[1-9]\\d*(\\.\\d+)?$")){
			return true; 
		}
		else{
			return false;
		}	
	}

}