package com.howtodoinjava.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.support.Repositories;
import org.springframework.util.ReflectionUtils;

import com.google.gson.Gson;
import com.howtodoinjava.demo.repository.EmployeeRepository;
import com.howtodoinjava.nk.CassRepoAndServiceLoaderFactory;

@SpringBootApplication
public class DemoApplication {
	public static ConfigurableApplicationContext ctx;
	@Autowired
	static EmployeeRepository repo;
	public static void main(String[] args) {
//		CassRepoAndServiceLoaderFactory.launchFactory();
//		SpringApplication.run(DemoApplication.class, args);
		ctx = SpringApplication.run(DemoApplication.class, args);
//		ApplicationContext context = event.getApplicationContext();
	    Gson gson = new Gson();
	    System.out.println("*****************************************************************************************************");
	    GenericRepository();
	    System.out.println(gson.toJson(ctx.getApplicationName()));
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		try {
//			CassRepoAndServiceLoaderFactory.factory().modelSetups.get("EmployeeEntity").svcs.getAll();
//		}catch (NullPointerException e) {
//			System.err.println(e.getMessage());
//		}
//		
	}
	
	
	 public static void GenericRepository() {
	    	Repositories repositories = new Repositories(ctx.getBeanFactory());
	    	Iterator it = repositories.iterator();
	    	 while(it.hasNext()) {
	    	  Class<?> domainClass = (Class<?>) it.next();
	    	  System.out.println(domainClass.getSimpleName());
	    	  //Get Repositories
	    	  repositories.getRepositoryFor(domainClass);
	    	  //Get Query Methods
	    	  List<QueryMethod> methods = repositories.getQueryMethodsFor(domainClass);
	    	  
	    	  methods.forEach( method ->{
	    		  System.out.println(method);
	    	  });
	    	  System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	    	  Advised repoProxy = (Advised)repositories.getRepositoryFor(domainClass).get();
	    	    Class<?>[] interfaces = repoProxy.getProxiedInterfaces();
	    	    
	    	    for (Class<?> class1 : interfaces) {
	    	    	if(class1.equals(EmployeeRepository.class)) {
	    	    		System.out.println("##########################");
		    	    	System.out.println(class1.getName());
		    	    	Method[] methods1 = ReflectionUtils.getAllDeclaredMethods(class1);
		    	    	for (Method class2 : methods1) {
							System.out.println(class2.getName());
							if(class2.getName().equals("findAll")) {
								try {
									class2.invoke(repo, 1);
								} catch (IllegalAccessException | IllegalArgumentException
										| InvocationTargetException e) {
									e.printStackTrace();
								}
							}
						}
	    	    	}
	    	    	
				}
	    	  
	    	 }
//	        repositories = new Repositories(appContext.fac);
	    }

}
