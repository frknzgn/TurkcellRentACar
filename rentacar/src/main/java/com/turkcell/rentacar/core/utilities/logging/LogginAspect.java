package com.turkcell.rentacar.core.utilities.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogginAspect {
	
	@Before("allRequests()")
	public void loggingAdvice(JoinPoint joinPoint) {
		
		System.out.println(joinPoint.getTarget());
		
	}
	
	@After("args(String)")
	public void stringArgumentMethods() {
		
	}
	
	@Pointcut("within(com.turkcell.rentacar.api.controller)")
	public void allRequests() {}
	
	@Pointcut("execution(* add*())")
	public void allAddMethods() {}
	
	@Pointcut("excecution(* com.turkcell.*.*(..))")
	public void selectAll() {}
	
}
