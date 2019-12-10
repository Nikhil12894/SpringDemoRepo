package com.howtodoinjava.demo.repository;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericRepository {

    @Autowired
    private WebApplicationContext appContext;

    Repositories repositories = null;

    public GenericRepository() {
    	
    	
    	Repositories repositories = new Repositories(appContext.getBeanFactory());
    	Iterator it = repositories.iterator();
    	 while(it.hasNext()) {
    	  Class<?> domainClass = (Class<?>) it.next();
    	  //Get Repositories
    	  repositories.getRepositoryFor(domainClass);
    	  //Get Query Methods
    	  List<QueryMethod> methods = repositories.getQueryMethodsFor(domainClass);
    	 }
        repositories = new Repositories(appContext.fac);
    }

	public JpaRepository getRepository(Class<?> entity) {
        return (JpaRepository) repositories.getRepositoryFor(entity).get();
    }

	public Object save(Class<?> entity) {
        return getRepository(entity).save(entity);
    }

    public Object findAll(Class<?> entity) {
        return getRepository(entity).findAll();
    }

    public void delete(Class<?> entity) {
        getRepository(entity).delete(entity);
    }
    
}