package com.howtodoinjava.nk;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ConfigurableApplicationContext;

import com.howtodoinjava.anotation.EntityRepoServiceMapper;
import com.howtodoinjava.demo.DemoApplication;
import com.howtodoinjava.demo.repository.BaseRepository;

@SuppressWarnings("rawtypes")
public class CassRepoAndServiceLoaderFactory {
	ConfigurableApplicationContext ctx;
	private static short instanceNum = 0;
	public ConcurrentHashMap<String, EntityModelSetup> modelSetups = new ConcurrentHashMap<>();

	public CassRepoAndServiceLoaderFactory() {
		if (instanceNum > 0) {
			throw new RuntimeException("CassRepoAndServiceLoaderFactory instance no "+ instanceNum);
		}
		instanceNum++;
	}

	private static CassRepoAndServiceLoaderFactory factory;

	public static synchronized void launchFactory( ConfigurableApplicationContext ctx) {
		System.out.println("Fcatory @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		if (factory == null) {
			factory= new CassRepoAndServiceLoaderFactory();
			factory.ctx=ctx;
			factory.loadResources();
		}
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
			entityModelSetup.repository = (BaseRepository) this.ctx.getBean(obj.repoClass());
			if(modelSetups!=null && !modelSetups.containsKey(entityName)) {
				modelSetups.put(entityName,entityModelSetup);
			}
		}
	}
	
	
}
