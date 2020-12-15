package com.holiday.matcloud;

import com.holiday.matcloud.core.JsonApplicationContext;
import com.holiday.matcloud.object.Person;

public class Application {
	public static void main(String[] args) throws Exception {
	    JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Person person = (Person) applicationContext.getBean("person");
        person.doSomeThing();
	}
}
