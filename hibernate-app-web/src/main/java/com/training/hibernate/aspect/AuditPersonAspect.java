package com.training.hibernate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import com.training.hibernate.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import com.training.hibernate.services.PersonAuditService;
import com.training.hibernate.dto.PersonDto;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuditPersonAspect {

	@Autowired
	private PersonAuditService personAuditService;

	@Pointcut("execution(* com.training.hibernate.services.PersonService.savePerson(..))")
	public void save(){};

	@Pointcut("execution(* com.training.hibernate.services.PersonService.updatePerson(..))")
	public void update(){};

	@Pointcut("execution(* com.training.hibernate.services.PersonService.deletePerson(..))")
	public void delete(){};

	 @AfterReturning(
	 	pointcut = "save() || update() || delete()",
      returning= "result")
   	public void logAfterReturning(JoinPoint joinPoint, Object result) {

		System.out.println("saving person log");
		System.out.println("Method returned value is : " + result);
		Status status = null;
		if(joinPoint.getSignature().getName().equals("savePerson")){
			status = Status.ADDED;
		}
		else if(joinPoint.getSignature().getName().equals("updatePerson")){
			status = Status.MODIFIED;
		}
		else{
			status = Status.DELETED;
		}
		personAuditService.savePersonAudit((PersonDto)result, status);
   }

}