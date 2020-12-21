package com.holiday.matcloud.object;

public class DemoServiceImpl implements DemoService {

	public DemoServiceImpl() {
		
	}
	
	@Override
	public void add(int a, int b) {
		System.err.println(a+b);
	}

}
