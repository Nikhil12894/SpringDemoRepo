package com.howtodoinjava.nk;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
//					loadEntityModel(classobj);
					System.out.println(classobj);
				});
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
	}
	
	
	private void loadEntityModel(Class<?> classObj){
		if(classObj.isAnnotationPresent(EntityRepoServiceMapper.class)) {
			EntityRepoServiceMapper obj=classObj.getAnnotation(EntityRepoServiceMapper.class);
			EntityModelSetup entityModelSetup = new EntityModelSetup();
			entityModelSetup.id=obj.iid();
			entityModelSetup.service=obj.serviceClass();
			entityModelSetup.repository=obj.repoClass();
			if(modelSetups!=null && !modelSetups.containsKey(obj.iid())) {
				modelSetups.put(obj.iid(),entityModelSetup);
			}
		}
	}
}
