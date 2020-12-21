package com.holiday.matcloud.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class LogAdvisor implements InvocationHandler{

	private Object target;
	
	private List<String> methodName;
	
    public LogAdvisor(Object paramsTarget, List<String> methodName) {
        this.target = paramsTarget;
        this.methodName = methodName;
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (this.methodName.contains(method.getName())) {
			System.err.println("执行方法："+method.getName() + "参数列表："+Arrays.toString(args));	
		}
		return method.invoke(target, args);
	}

}
