package com.howtodoinjava.nk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.howtodoinjava.demo.model.BaseEntity;
import com.howtodoinjava.demo.service.BaseEntityService;

public class EntityModelSetup<T extends BaseEntity> {
	
	
	public <T> EntityModelSetup(Class<T> obj){
		System.out.println(obj);
	}
	public String id;
	
	public Class<T> repository;
	
	public Class<T> service;
	
	public Class<T> entityClass;
	
	public BaseEntityService<T> svcs;

	@SuppressWarnings("unchecked")
	public void initSetup() {
		try {
			Constructor<T> obj= this.service.getConstructor();
			svcs=(BaseEntityService<T>) obj.newInstance();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
