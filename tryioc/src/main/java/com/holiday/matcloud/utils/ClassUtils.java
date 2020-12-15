package com.holiday.matcloud.utils;

public class ClassUtils {

	public static ClassLoader getDefultClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	@SuppressWarnings("rawtypes")
	public static Class loadClass(String classname) {
		try {
			return getDefultClassLoader().loadClass(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
