package com.howtodoinjava.nk;

import org.springframework.beans.factory.annotation.Autowired;

public class EntityModelSetup {
	
	public String id;
	
	@Autowired
    public Class<?> repository;
	
	@Autowired
	public Class<?> service;
	
}
