package com.holiday.matcloud.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import com.holiday.matcloud.bean.BeanDefinition;
import com.holiday.matcloud.bean.ConstructorArg;
import com.holiday.matcloud.utils.BeanUtils;
import com.holiday.matcloud.utils.ClassUtils;
import com.holiday.matcloud.utils.ReflectionUtils;

public class BeanFactoryImpl implements BeanFactory{

    private static final ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<String, Object>();
     
    private static final ConcurrentHashMap<String,BeanDefinition> beanDefineMap= new ConcurrentHashMap<String, BeanDefinition>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());
    
	public Object getBean(String name) throws Exception {
		// TODO Auto-generated method stub
		Object bean = beanMap.get(name);
		if (bean != null) {
			return bean;
		}
		// 如果实例好的beanMap找不到bean  就先创建再返回
		bean = createBean(beanDefineMap.get(name));
	
		
		if(bean != null) {
			populatebean(bean);
			beanMap.put(name, bean);
		}
				
		return bean;
	}
	
	public void registerBean(String beanName, BeanDefinition beanDefinition) {
		beanDefineMap.put(beanName, beanDefinition);
		beanNameSet.add(beanName);
	}
	
	private Object createBean(BeanDefinition beanDefinition) throws Exception{
		String beanClassName = beanDefinition.getClassName();
		Class clz = ClassUtils.loadClass(beanClassName);
		if (clz == null) {
            throw new Exception("can not find bean by beanName");
		}
		List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
		if (constructorArgs != null && !constructorArgs.isEmpty()) {
			List<Object> objects = new ArrayList<>();
			for (ConstructorArg constructorArg : constructorArgs) {
				objects.add(getBean(constructorArg.getRef()));
			}
			return BeanUtils.instanceByCglib(clz, clz.getConstructor(), objects.toArray());
		} else {
            return BeanUtils.instanceByCglib(clz,null,null);
		}
	}
	
	public void populatebean(Object bean) throws Exception {
		Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				String beanName = field.getName();
				// 首字符小写  IOC容器默认这个规则
				if (beanNameSet.contains(beanName)) {
					beanName = StringUtils.uncapitalize(beanName);
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                    	//如果类的属性（成员变量） 的名字 在Bean容器中存在   就把他赋值给成员变量   相当于依赖注入
                        ReflectionUtils.injectField(field,bean,fieldBean);
                    }
				}
			}
		}
	}

}
