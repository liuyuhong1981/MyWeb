/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年1月18日
 */
package org.lyh.myweb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

	@Pointcut("execution(* org.lyh.myweb.service..*(..))")
	public void aspect() {
	}

	@Around("aspect()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("around advice: A request was issued at " + proceedingJoinPoint.toLongString());
		return proceedingJoinPoint.proceed();
	}
}
