package com.holiday.matcloud.BeanDefinitionRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class AnimalNameSetterPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] animalNames = beanFactory.getBeanNamesForType(Animal.class);
        for (String beanName: animalNames) {
        	BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
        	beanDefinition.getPropertyValues().add("name", beanName);
        }       
        System.out.println("AnimalNameSetterPostProcessor postProcessBeanFactory run");
	}

}
