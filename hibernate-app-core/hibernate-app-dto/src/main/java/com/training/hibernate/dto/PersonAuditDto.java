package com.training.hibernate.dto;

import java.util.Date;
import com.training.hibernate.model.Status;

public class PersonAuditDto extends BaseDto {

	private int personId;

	private Status status;

	private Date currentDate;

	public int getPersonId(){
		return this.personId;
	}

	public void setPersonId(int personId){
		this.personId = personId;
	}
	
	public Status getStatus(){
		return this.status;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Date getCurrentDate(){
		return this.currentDate;
	}

	public void setCurrentDate(Date currentDate){
		this.currentDate = currentDate;
	}
}