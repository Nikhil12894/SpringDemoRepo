package com.howtodoinjava.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.howtodoinjava.demo.model.BaseEntity;
 
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity>
        extends CrudRepository<T, Long> {

	T findById(long id);

	List<T> findAll();
}
