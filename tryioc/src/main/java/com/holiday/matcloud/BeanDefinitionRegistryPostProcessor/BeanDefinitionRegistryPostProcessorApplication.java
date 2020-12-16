package com.holiday.matcloud.BeanDefinitionRegistryPostProcessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionRegistryPostProcessorApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				"com.holiday.matcloud.BeanDefinitionRegistryPostProcessor");
		// cat是由Component注解的   Dog是没有的 是通过注册BeanDefinition来的
		Cat cat = ctx.getBean(Cat.class);
		System.out.println(cat);
		Dog dog = ctx.getBean(Dog.class);
		System.out.println(dog);
	}
}
