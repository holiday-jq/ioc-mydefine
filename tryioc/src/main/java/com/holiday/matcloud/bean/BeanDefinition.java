package com.holiday.matcloud.bean;

import java.util.List;

public class BeanDefinition {
	
    private String name;
    private String className;
    private List<ConstructorArg> constructorArgs;
    private List<PropertyArg> propertyArgs;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<ConstructorArg> getConstructorArgs() {
		return constructorArgs;
	}
	public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
		this.constructorArgs = constructorArgs;
	}
	public List<PropertyArg> getPropertyArgs() {
		return propertyArgs;
	}
	public void setPropertyArgs(List<PropertyArg> propertyArgs) {
		this.propertyArgs = propertyArgs;
	}
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }   
}
