/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年1月18日
 */
package org.lyh.myweb.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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

	@AfterThrowing(pointcut = "execution(* com.gkatzioura.spring.aop.service.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		String message = "";
		if(error.getMessage().contains("my")) {
			message = "get my exception message and change it !";
		} else {
			message = error.getMessage();
		}
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		String methodName = method.getName();
		StringBuilder builder = new StringBuilder(512);
		builder.append("\nMethod Name is :").append(methodName).append("\n");
		if (args != null) {
			int size = args.length;
			builder.append("Method Args:\n");
			for (int index = 0; index < size; index++) {
				Object object = args[index];
				builder.append("args[").append(index).append("]:").append(object.toString()).append("\n");
			}
		}
		builder.append(message);
		throw new RuntimeException(builder.toString());
	}
}
