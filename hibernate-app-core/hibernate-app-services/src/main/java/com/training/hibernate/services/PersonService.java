package com.training.hibernate.services;

import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.dto.RoleDto;
import java.util.List;

public interface PersonService {

	public List<PersonDto> getAllPersons();

	public PersonDto getPersonById(int id);

	public PersonDto saveOrUpdatePerson(PersonDto personDto);

	public PersonDto deletePerson(int id);

	public List<PersonDto> searchPerson(String lastName, String firstName, String middleName, String role);

	public List<RoleDto> getRoles();

	public PersonDto createPersonDto(String id, String firstName, String middleName, String lastName, String gender, String birthdate,
		String employed, String gwa, String street, String houseNo, String barangay, String subdivision,
		String city, String zipCode, String[] contactTypeList, String[] contactValueList, String[] contactId,
		String[] roleList, String[] savedContactValueList, String[] savedContactTypeList);

	public boolean isRequired(String firstName, String middleName, String lastName, String gender, String employed,
		String street, String barangay, String subdivision, String city, String zipCode,
		String[] contactTypeList, String[] contactValueList, String[] roleList);

	public boolean isDate(String strDate);

	public boolean isNumber(String strNo);

	public boolean isDecimal(String strDecimal);

}