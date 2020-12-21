package com.holiday.matcloud;

import com.holiday.matcloud.core.JsonApplicationContext;
import com.holiday.matcloud.object.DemoService;
import com.holiday.matcloud.object.Person;

public class Application {
	public static void main(String[] args) throws Exception {
	    JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Person person = (Person) applicationContext.getBean("person");
        person.doSomeThing();
        //返回动态代理的bean  增强日志方法
        DemoService demoService = (DemoService) applicationContext.getBean("demoService");
        demoService.add(1, 2);
	}
}
