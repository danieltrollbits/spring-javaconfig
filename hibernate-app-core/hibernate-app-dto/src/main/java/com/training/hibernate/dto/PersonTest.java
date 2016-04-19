package com.training.hibernate.dto;

import java.util.*;
import com.training.hibernate.model.Gender;

public class PersonTest extends BaseDto {

	private String lastName;

	private String firstName;

	private String middleName;

	private Gender gender;
	
	private Date birthdate;

	public PersonTest(){};

	public PersonTest(String lastName, String firstName, String middleName, Gender gender){
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public String getLastName(){
		return this.lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getMiddleName(){
		return this.middleName;
	}

	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	public String getFullName(){
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}

	public Gender getGender(){
		return this.gender;
	}

	public void setGender(Gender gender){
		this.gender = gender;
	}

	public Date getBirthdate(){
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate){
		this.birthdate = birthdate;
	}
}
