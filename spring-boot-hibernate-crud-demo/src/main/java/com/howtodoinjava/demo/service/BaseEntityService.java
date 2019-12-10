package com.howtodoinjava.demo.service;

import java.util.List;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.BaseEntity;
 
public interface BaseEntityService<T extends BaseEntity> {
     
	    public List<T> getAll();
	     
	    public T getEmployeeById(Long id) throws RecordNotFoundException;
	     
	    public T createOrUpdate(T entity) throws RecordNotFoundException;
	   
	     
	    public void deleteById(Long id) throws RecordNotFoundException;
}