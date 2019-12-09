package com.howtodoinjava.demo.repository;

import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.demo.model.EmployeeEntity;

@Transactional
public interface EmployeeRepository
        extends BaseRepository<EmployeeEntity> {
 
}