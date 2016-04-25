package com.training.hibernate.model;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PERSON_AUDIT")
public class PersonAudit extends BaseEntity {

	public PersonAudit(){};

	@Column(name = "person_id")
	private int personId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_audited")
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