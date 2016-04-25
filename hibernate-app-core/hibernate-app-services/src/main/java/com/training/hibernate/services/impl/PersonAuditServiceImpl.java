package com.training.hibernate.services.impl;

import com.training.hibernate.model.PersonAudit;
import com.training.hibernate.dto.PersonAuditDto;
import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.model.Status;
import com.training.hibernate.dao.PersonAuditDao;
import com.training.hibernate.services.PersonAuditService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.beanutils.BeanUtils;
import java.util.List;
import java.util.ArrayList;

@Service
public class PersonAuditServiceImpl implements PersonAuditService {

	@Autowired
	private PersonAuditDao personAuditDao;

	@Override
	public PersonAudit savePersonAudit(PersonDto person, Status status){
		return personAuditDao.savePersonAudit(toAudit(person,status));
	}

	public PersonAudit toAudit(PersonDto person, Status status){
		PersonAudit personAudit = new PersonAudit();
		personAudit.setPersonId(person.getId());
		personAudit.setStatus(status);
		// personAudit.setCurrentDate(new Date());
		return personAudit;
	}

	public PersonAuditDto toDto(PersonAudit personAudit){
		PersonAuditDto personAuditDto = new PersonAuditDto();
		try{
			BeanUtils.copyProperties(personAuditDto,personAudit);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return personAuditDto;
	}

	public List<PersonAuditDto> toDtos(List<PersonAudit> personAudits){
		List<PersonAuditDto> personAuditDtos = new ArrayList<>();
		for (PersonAudit personAudit : personAudits){
			personAuditDtos.add(toDto(personAudit));
		}
		return personAuditDtos;
	}

	public List<PersonAuditDto> list(){
		return toDtos(personAuditDao.list());
	}

}