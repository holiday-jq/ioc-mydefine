package com.holiday.matcloud.utils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class BeanUtils {
	@SuppressWarnings("unchecked")
	public static <T> T instanceByCglib(Class<T> clz, Constructor ctr,Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);
        if(ctr == null){
            return (T) enhancer.create();
        }else {
            return (T) enhancer.create(ctr.getParameterTypes(),args);
        }
    }
}
