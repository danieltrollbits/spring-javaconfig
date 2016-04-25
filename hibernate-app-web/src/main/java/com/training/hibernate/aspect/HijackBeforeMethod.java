package com.training.hibernate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class HijackBeforeMethod {

	@Before("execution(* com.training.hibernate.services.CustomerService.printName(..))")
	public void logBefore(JoinPoint joinPoint) {

		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("hijacked : " + joinPoint.getTarget());
		System.out.println("hijacked : " + joinPoint.getThis());		
		System.out.println("******");
	}

}
