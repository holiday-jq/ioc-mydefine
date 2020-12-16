package com.holiday.matcloud.BeanFactoryPostProcessor;

import org.springframework.stereotype.Component;

@Component
public class Green extends Color {

	@Override
	public String toString() {
		return "Green{" + "name='" + name + '\'' + "}";
	}
}
