package com.tony.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.context.ApplicationContext;

public class Main {
public static void main(String[] args) {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	ArithmeticCalulator arithmeticCalulator = (ArithmeticCalulator)ctx.getBean("arithmeticCalulator");
	
	int result = arithmeticCalulator.add(1, 2);
	System.out.println(result);
	
	result = arithmeticCalulator.div(1000, 10);
	System.out.println(result);
}
}
