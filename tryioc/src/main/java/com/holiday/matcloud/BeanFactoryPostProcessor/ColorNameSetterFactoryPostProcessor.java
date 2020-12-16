package com.holiday.matcloud.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * BeanFactoryPostProcessor 是IOC容器的扩展点、它用于IOC容器的生命周期中,
 * 所有的BeanDefinition都注册到BeanFactory后回调触发，用于访问/修改已经存在的BeanDefinition
 * 关键点： 改成Bean的定义信息
 */
@Component
public class ColorNameSetterFactoryPostProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String []beanDefinitionName = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanDefinitionName) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), 
            		this.getClass().getClassLoader()).getSuperclass().equals(Color.class)) {
            	 // 判断Class类型是否是Color  给BeanDefinition添加属性 
            	System.err.println(beanDefinition.getAttribute("name")); //类成员变量中没有的属性
            	beanDefinition.getPropertyValues().add("name", beanName);
            }
		}
	}

}
