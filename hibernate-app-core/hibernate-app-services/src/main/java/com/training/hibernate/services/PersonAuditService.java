package com.training.hibernate.services;

import com.training.hibernate.model.Status;
import com.training.hibernate.model.PersonAudit;
import com.training.hibernate.dto.PersonAuditDto;
import com.training.hibernate.dto.PersonDto;
import java.util.List;

public interface PersonAuditService {
	public PersonAudit savePersonAudit(PersonDto person, Status status);

	public List<PersonAuditDto> list();
}