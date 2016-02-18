package com.tony.spring.aop;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class LoggingAspect {

	
	//Define a method for annotation expression. In general, this method does not need add other code
	/**
	 * Use @Pointcut to define
	 * 
	 */
	@Pointcut("execution(public int com.tony.spring.aop.ArithmeticCalulator.*(..))")
	public void declareJointPointExpression(){}
	
	 /**
	 * Want to execute this method whenever
	 * every instance object of "com.tony.spring.aop.ArithmeticCalulatorImpl"
	 * runs every method
	 *
	 */
	 @Before("declareJointPointExpression()")
	 public void beforeMethod(JoinPoint joinPoint){
	 String methodName = joinPoint.getSignature().getName();
	 Object[] args = joinPoint.getArgs();
	 System.out.println("The method begins " + methodName + " with " +
	 Arrays.asList(args));
	
	 }
	
	 /**
	 * Execute after target method, no matter it is exception or not
	 *
	 * @param joinPoint
	 */
	 @After("declareJointPointExpression()")
	 public void afterMethod(JoinPoint joinPoint){
	 String methodName = joinPoint.getSignature().getName();
	 System.out.println("The method end with " + methodName );
	
	 }
	
	
	 /**
	 * Just execute when the target method runs legally
	 * It can receive return value
	 *
	 * @param joinPoint
	 */
	 @AfterReturning(value="declareJointPointExpression()", returning="result")
	 public void afterReturning(JoinPoint joinPoint, Object result){
	 String methodName = joinPoint.getSignature().getName();
	 System.out.println("The method end with " + methodName + " with " +
	 result);
	
	 }
	
	 /**
	 * It executes when target method has exceptions
	 * Can run it for specific exceptions
	 *
	 *
	 * @param joinPoint
	 * @param ex
	 */
	 @AfterThrowing(value="declareJointPointExpression()", throwing="ex")
	 public void afterThrowing(JoinPoint joinPoint, NullPointerException ex){
	
	 String methodName = joinPoint.getSignature().getName();
	 System.out.println("The method " + methodName + " occurs exception " +
	 ex);
	
	
	 }
	

//	/**
//	 * "Around" annotation needs to come with ProceedingJoinPoint type
//	 * parameters It is similar with "dynamic agent": "ProceedingJoinPoint" type
//	 * is the key of method execution And around annotation must have return
//	 * values, it is the target return value
//	 * 
//	 * @param pjd
//	 */
//	@Around("execution(public int com.tony.spring.aop.ArithmeticCalulator.*(..))")
//	public Object aroundMethod(ProceedingJoinPoint pjd) {
//
//		Object result = null;
//		String methodName = pjd.getSignature().getName();
//		// Execute target method
//
//		try {
//			// Before method
//			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
//			result = pjd.proceed();
//			// After method
//			System.out.println("The method" + methodName + " ends with " + result);
//
//		} catch (Throwable e) {
//
//			// Exception notice
//			System.out.println("The method " + methodName + " occurs exception: " + e);
//			throw new RuntimeException(e);
//		}
//
//		System.out.println("The method" + methodName + " ends");
//
//		return result;
//	}

}
