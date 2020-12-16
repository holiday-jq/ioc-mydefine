package com.holiday.matcloud.BeanFactoryPostProcessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactoryProcessorApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				"com.holiday.matcloud.BeanFactoryPostProcessor");
		Red red = ctx.getBean(Red.class);
		System.out.println(red);
	}
}
