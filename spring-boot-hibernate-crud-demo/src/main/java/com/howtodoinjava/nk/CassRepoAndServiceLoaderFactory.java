package com.howtodoinjava.nk;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.GenericTypeResolver;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.support.Repositories;

import com.howtodoinjava.anotation.EntityRepoServiceMapper;

public class CassRepoAndServiceLoaderFactory {

	
	
	private static short instanceNum = 0;
	public ConcurrentHashMap<String, EntityModelSetup> modelSetups = new ConcurrentHashMap<>();

	public CassRepoAndServiceLoaderFactory() {
		if (instanceNum > 0) {
			throw new RuntimeException("CassRepoAndServiceLoaderFactory instance no "+ instanceNum);
		}
		instanceNum++;
	}

	private static CassRepoAndServiceLoaderFactory factory;

	public static synchronized CassRepoAndServiceLoaderFactory launchFactory() {
		if (factory == null) {
			factory= new CassRepoAndServiceLoaderFactory();
			factory.loadResources();
		}
		return factory;
	}

	public static CassRepoAndServiceLoaderFactory factory() {
		return factory;
	}

	@SuppressWarnings("unchecked")
	private void loadResources() {
		ResourceLoader resource = ResourceLoader.getResourceLoader();
		Map<String,Object> map= resource.redResourceAsJson("META-INF/config.json");
		ArrayList<String> list1=(ArrayList<String>) map.get("packageArray");
		list1.forEach(packageName->{
			try {
				Set<Class<?>> classList = EntityModelLoader.getClassesForPackage(packageName);
				classList.forEach( classobj ->{
					loadEntityModel(classobj);
//					System.out.println(classobj);
				});
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
	}
	
	
	private void loadEntityModel(Class<?> classObj){
		String entityName = classObj.getSimpleName();
		if(classObj.isAnnotationPresent(EntityRepoServiceMapper.class)) {
			EntityRepoServiceMapper obj=classObj.getAnnotation(EntityRepoServiceMapper.class);
			EntityModelSetup entityModelSetup = new EntityModelSetup(classObj);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(entityName);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
			entityModelSetup.id=entityName;
			entityModelSetup.entityClass=classObj;
			entityModelSetup.service=obj.serviceClass();
			entityModelSetup.repository=obj.repoClass();
			entityModelSetup.initSetup();
			if(modelSetups!=null && !modelSetups.containsKey(entityName)) {
				modelSetups.put(entityName,entityModelSetup);
			}
		}
	}
	
	
}
