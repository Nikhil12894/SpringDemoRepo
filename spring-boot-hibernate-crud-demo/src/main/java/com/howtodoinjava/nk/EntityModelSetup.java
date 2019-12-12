package com.howtodoinjava.nk;

import com.howtodoinjava.demo.model.BaseEntity;
import com.howtodoinjava.demo.repository.BaseRepository;
import com.howtodoinjava.demo.service.BaseEntityService;

@SuppressWarnings("hiding")
public class EntityModelSetup<T extends BaseEntity> {
	public <T> EntityModelSetup(Class<T> obj){
		System.out.println(obj);
	}
	public String id;
	
	public BaseRepository<T> repository;
	
	public Class<T> service;
	
	public Class<T> entityClass;
	
	public BaseEntityService<T> svcs;
}
