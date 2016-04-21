package com.training.hibernate.dto;

import java.util.*;
import com.training.hibernate.model.Gender;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.NotBlank;

public class PersonDto extends BaseDto {

	@NotBlank(message = "Please enter your last name.")
	private String lastName;

	@NotBlank(message = "Please enter your last name.")
	private String firstName;

	@NotBlank(message = "Please enter your middle name.")
	private String middleName;

	@NotNull(message = "Please select gender.")
	private Gender gender;
	
	@NotNull(message = "Please enter your birthdate.")
	@Past(message = "Invalid date.")
	private Date birthdate;
	
	@Valid
	private AddressDto addressDto;

	@NotNull(message = "Please select employment status.")
	private boolean employed;
	
	@NotNull(message = "Please enter your gwa.")
	private float gwa;
	
	private List<ContactDto> contactDtos;
	
	@NotNull(message = "Please select role.")
	private List<RoleDto> roleDtos = new ArrayList<RoleDto>(0);

	public PersonDto(){};

	public PersonDto(String lastName, String firstName, String middleName, Gender gender, Date birthdate, boolean employed, float gwa){
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.employed = employed;
		this.gwa = gwa;
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

	public AddressDto getAddressDto(){
		return this.addressDto;
	}

	public void setAddressDto(AddressDto addressDto){
		this.addressDto = addressDto;
	}

	public boolean isEmployed(){
		return this.employed;
	}

	public void setEmployed(boolean employed){
		this.employed = employed;
	}

	public float getGwa(){
		return this.gwa;
	}

	public void setGwa(float gwa){
		this.gwa = gwa;
	}

	public List<ContactDto> getContactDtos(){
		return this.contactDtos;
	}

	public void setContactDtos(List<ContactDto> contactDtos){
		this.contactDtos = contactDtos;
	}

	public List<RoleDto> getRoleDtos(){
		return this.roleDtos;
	}

	public void setRoleDtos(List<RoleDto> roleDtos){
		this.roleDtos = roleDtos;
	}

	public String toString(){
		return "name: " + getFullName() + "gender: " + this.gender + "birthdate: " + this.birthdate;
	}

	public String rolesToString(){
		String roles = "";
		int loop = 1;
		int size = this.roleDtos.size();
		for (RoleDto roleDto : this.roleDtos){
			roles += roleDto.getRole();
			if (loop >= 0 && loop <size)
				roles += ", ";
			loop++;
		}
		return roles;
	}
}
