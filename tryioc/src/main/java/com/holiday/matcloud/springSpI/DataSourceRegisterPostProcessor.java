package com.holiday.matcloud.springSpI;

import java.sql.Driver;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import com.alibaba.druid.pool.DruidDataSource;
/**
 * Spring框架的SPI机制  通过SpringFactoriesLoader读取META-INF下spring.factories
 *
 */
public class DataSourceRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware{

	protected Environment environment;
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      //会比普通类实现的BeanFactoryPostProcessor先执行		
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;	
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(DruidDataSource.class)
			   .addPropertyValue("url", environment.getProperty("jdbc.url"))
	           .addPropertyValue("username", environment.getProperty("jdbc.username"))
	           .addPropertyValue("password", environment.getProperty("jdbc.password"));
		List<String> driverClassNames = SpringFactoriesLoader.loadFactoryNames(Driver.class, 
				this.getClass().getClassLoader());
		String driverClassName = null;
		for (String className: driverClassNames) {
		   try {
	            Class.forName(className);
	            driverClassName = className;
	            break;
	        } catch (ClassNotFoundException ignored) {
	            // 加载失败，classpath下无当前驱动，继续下一个
	        	ignored.printStackTrace();
	        }
		}
		 // 存在驱动，注册DataSource
	    if (driverClassName != null) {
	    	beanDefinitionBuilder.addPropertyValue("driverClassName", driverClassName);
	        registry.registerBeanDefinition("dataSource", beanDefinitionBuilder.getBeanDefinition());
	    }
	}

}
