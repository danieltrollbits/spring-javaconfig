package com.training.hibernate.dao;

import com.training.hibernate.model.PersonAudit;
import java.util.List;

public interface PersonAuditDao {

	public PersonAudit savePersonAudit(PersonAudit personAudit);

	public List<PersonAudit> list();

}