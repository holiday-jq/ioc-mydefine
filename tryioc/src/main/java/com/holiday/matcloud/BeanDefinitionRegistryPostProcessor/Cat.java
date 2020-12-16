package com.holiday.matcloud.BeanDefinitionRegistryPostProcessor;

import org.springframework.stereotype.Component;

@Component
public class Cat extends Animal {

	@Override
	public String toString() {
		return "Cat{" + "name='" + name + '\'' + "}";
	}
}
