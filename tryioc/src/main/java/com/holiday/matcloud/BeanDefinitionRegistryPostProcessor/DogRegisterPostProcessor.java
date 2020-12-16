package com.holiday.matcloud.BeanDefinitionRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;
/**
 * 容器扩展点，用于IOC容器的生命周期中，所有的BeanDefinition都准备好，即将加载到BeanFactory
 * 时触发回调，它用于给BeanFactory中添加新的BeanDefinition.
 * 关键点回答:注册新的Bean的定义信息
 */
@Component
public class DogRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor{

	/**
	 * BeanDefinitionRegistryPostProcessor接口 继承了BeanFactory接口
	 * 所以同时实现两个接口 一个用来注册 一个用来修改
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	   System.out.println("DogRegisterPostProcessor postProcessBeanFactory run");
	   //比普通的BeanFactoryPostProcessor 先执行
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		 if (!registry.containsBeanDefinition("dog")) {
	        // 构造BeanDefinition，并注册进BeanFactory
            BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Dog.class).getBeanDefinition();
            registry.registerBeanDefinition("dog", beanDefinition);
		 }
		 System.out.println("DogRegisterPostProcessor postProcessBeanDefinitionRegistry run");
	}

}
