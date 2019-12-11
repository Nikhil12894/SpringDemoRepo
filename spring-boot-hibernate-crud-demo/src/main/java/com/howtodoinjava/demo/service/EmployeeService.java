package com.howtodoinjava.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;
import com.howtodoinjava.demo.repository.GenericRepository;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EmployeeService implements BaseEntityService<EmployeeEntity> {

	@Autowired
	EmployeeRepository reposetory;
	
	public EmployeeService() {
	}
	
	@Override
	public List<EmployeeEntity> getAll() {
		return reposetory.findAll();
	}

	@Override
	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
		return null;
	}

	@Override
	public EmployeeEntity createOrUpdate(EmployeeEntity entity) throws RecordNotFoundException {
		
		return null;
	}

	@Override
	public void deleteById(Long id) throws RecordNotFoundException {

	}

}
